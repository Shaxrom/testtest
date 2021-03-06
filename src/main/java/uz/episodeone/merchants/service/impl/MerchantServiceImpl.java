package uz.episodeone.merchants.service.impl;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import uz.episodeone.merchants.client.PaymentInstrumentClient;
import uz.episodeone.merchants.client.PaynetClient;
import uz.episodeone.merchants.domain.Provider;
import uz.episodeone.merchants.domain.Service;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;
import uz.episodeone.merchants.dto.*;
import uz.episodeone.merchants.mapper.ServiceMapper;
import uz.episodeone.merchants.helpers.ErrorCode;
import uz.episodeone.merchants.helpers.Tools;
import uz.episodeone.merchants.exceptions.BadRequestException;
import uz.episodeone.merchants.repository.ProviderDAO;
import uz.episodeone.merchants.repository.ServiceDAO;
import uz.episodeone.merchants.service.MerchantService;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@org.springframework.stereotype.Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MerchantServiceImpl implements MerchantService {
    PaynetClient paynetClient;
    ServiceDAO merchantDAO;
    ServiceMapper serviceMapper;
    JacksonProperties jacksonProperties;
    HashMap<PaymentInstrument, PaymentInstrumentClient> instrumentClients;
    ProviderDAO providerDAO;
    ServiceDAO serviceDAO;

    @Autowired
    public MerchantServiceImpl(
            ServiceDAO merchantDAO,
            ServiceMapper serviceMapper,
            JacksonProperties jacksonProperties,
            @Lazy PaynetClient paynetClient, ProviderDAO providerDAO, ServiceDAO serviceDAO) {
        this.merchantDAO = merchantDAO;
        this.serviceMapper = serviceMapper;
        this.jacksonProperties = jacksonProperties;
        this.paynetClient = paynetClient;
        this.providerDAO = providerDAO;
        this.serviceDAO = serviceDAO;

        instrumentClients = new HashMap<>();
        instrumentClients.put(PaymentInstrument.PAYNET, paynetClient);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceDTO findOne(Long id) {
        return merchantDAO
                .readById(id)
                .map(service -> {
                    if (Objects.nonNull(service.getPaymentInstrument()) &&
                        service.getPaymentInstrument().equals(PaymentInstrument.PAYMART)) {
                        return serviceMapper.toDto(service);
                    } else {
                        return new ServiceDTO(
                                service,
                                instrumentClients.get(service.getPaymentInstrument()).getService(service.getPayInstServiceId()));
                    }
                })
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SERVICE_NOT_AVAILABLE.getValue()));
    }

    @Override
    @Transactional
    public ServiceDTO create(ServiceDTO merchantDto) {
        return serviceMapper.toDto(
                    merchantDAO.save(
                            serviceMapper.toEntity(merchantDto)));
    }


    @Override
    @Transactional
    public ServiceDTO update(ServiceDTO merchantDTO) {
        Service merchantToUpdate = merchantDAO
                .findById(merchantDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SERVICE_NOT_AVAILABLE.getValue()));
        serviceMapper.update(merchantDTO, merchantToUpdate);
        return serviceMapper.toDto(merchantDAO.save(merchantToUpdate));
    }


    @Override
    @Transactional
    public void delete(Long id) {
        merchantDAO.deleteById(id);
    }

    public List<ServiceDTO> getLastUpdated(LocalDateTime dateTime) {
        return merchantDAO.findAllNewlyUpdated(Tools.toInstant(dateTime, jacksonProperties.getTimeZone().getID()))
                .stream()
                .map(service -> {
                    if (service.getPaymentInstrument().equals(PaymentInstrument.PAYMART)) {
                        return serviceMapper.toDto(service);
                    } else {
                        return new ServiceDTO(
                                service,
                                instrumentClients.get(service.getPaymentInstrument()).getService(service.getPayInstServiceId()));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public MerchantServiceDetailsDto initBilling(InitBillingDto initBillingDto) {
        Service service = merchantDAO.findById(initBillingDto.getServiceId())
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND.getValue()));

        return MerchantServiceDetailsDto.builder()
                .humoMerchantId(service.getHumoMerchantId())
                .humoTerminalId(service.getHumoTerminalId())
                .uzcardMerchantId(service.getUzcardMerchantId())
                .uzcardTerminalId(service.getUzcardTerminalId())
                .build();

    }

    @Override
    public void submitBilling(SubmitPaymentDto submitPaymentDto) {

//        merchantDAO
//                .findById(submitPaymentDto.getServiceId())
//                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND.getValue()));

        List<RequestFieldDTO> collect = submitPaymentDto
                .getFields()
                .entrySet()
                .stream()
                .map(RequestFieldDTO::new)
                .collect(Collectors.toList());

        var perform = paynetClient.perform(
                submitPaymentDto.getServiceId(),
                Instant.now().toEpochMilli(),
                submitPaymentDto.getBillingTransactionId(),
                new PaymentRequestedFieldDTO(collect));

        log.info("[submitBilling] perform body: {}", perform);
//        return submitPaymentDto.getBillingTransactionId()
    }

    @Override
    public Page<ServiceDTO> getServices(Long providerId, Pageable pageable) {
        log.info("[getServices] provider id: {}",providerId);
        try {
            Provider provider = providerDAO.getById(providerId);
            return new PageImpl<>(serviceMapper.toDtoList(serviceDAO.findByProviderId(providerId, pageable).getContent()));
        } catch (javax.persistence.EntityNotFoundException e){
            throw new EntityNotFoundException(ErrorCode.NOT_FOUND.getValue());
        }
    }

    @Override
    public List<ServiceDTO> find(List<Long> ids, Filter filter) {
        List<Service> services = List.of();
        if (filter == null) {
            services = merchantDAO.findAll();
        } else if (ids != null) {
            switch (filter) {
                case CATEGORY:
                    services = merchantDAO.findByCategoryIdsAndActiveTrue(ids);
                    break;
                case SERVICE:
                    services = merchantDAO.findAllById(ids);
                    break;
                case PROVIDER:
                    services = merchantDAO.findByProviderIdIn(ids);
                    break;
            }
        } else {
            throw new IllegalStateException("Unexpected value");
        }

        return services
                .stream()
                .collect(Collectors.groupingBy(Service::getPaymentInstrument, Collectors.toList()))
                .entrySet()
                .stream()
                .map(entry -> instrumentClients
                        .get(entry.getKey())
                        .getServices(
                                entry
                                        .getValue()
                                        .stream()
                                        .map(Service::getPayInstServiceId)
                                        .collect(Collectors.toList())))
                .flatMap(Collection::stream)
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }
}
