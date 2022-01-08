package uz.episodeone.merchants.service.impl;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import uz.episodeone.merchants.client.PaymentInstrumentClient;
import uz.episodeone.merchants.client.PaynetClient;
import uz.episodeone.merchants.domain.Service;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;
import uz.episodeone.merchants.dto.Filter;
import uz.episodeone.merchants.dto.ServiceDTO;
import uz.episodeone.merchants.dto.mapper.ServiceMapper;
import uz.episodeone.merchants.helpers.ErrorCode;
import uz.episodeone.merchants.helpers.Tools;
import uz.episodeone.merchants.repository.ServiceDAO;
import uz.episodeone.merchants.service.ProviderServicesService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@org.springframework.stereotype.Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProviderServicesServiceImpl implements ProviderServicesService {
    ServiceDAO merchantDAO;
    ServiceMapper serviceMapper;
    JacksonProperties jacksonProperties;
    HashMap<PaymentInstrument, PaymentInstrumentClient> instrumentClients;

    @Autowired
    public ProviderServicesServiceImpl(
            ServiceDAO merchantDAO,
            ServiceMapper serviceMapper,
            JacksonProperties jacksonProperties,
            @Lazy PaynetClient paynetClient) {
        this.merchantDAO = merchantDAO;
        this.serviceMapper = serviceMapper;
        this.jacksonProperties = jacksonProperties;

        instrumentClients = new HashMap<>();
        instrumentClients.put(PaymentInstrument.PAYNET, paynetClient);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceDTO findOne(Long id) {
        return merchantDAO
                .readById(id)
                .map(service -> {
                    if (service.getPaymentInstrument().equals(PaymentInstrument.GLOBALPAY)) {
                        return serviceMapper.toDto(service);
                    } else {
                        return serviceMapper.toDto(
                                service,
                                instrumentClients.get(service.getPaymentInstrument()).getService(service.getPayInstServiceId()).getData());
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
                    if (service.getPaymentInstrument().equals(PaymentInstrument.GLOBALPAY)) {
                        return serviceMapper.toDto(service);
                    } else {
                        return serviceMapper.toDto(
                                service,
                                instrumentClients.get(service.getPaymentInstrument()).getService(service.getPayInstServiceId()).getData());
                    }
                })
                .collect(Collectors.toList());
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
                    services = merchantDAO.findByProviderIds(ids);
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
                                        .collect(Collectors.toList())).getData())
                .flatMap(Collection::stream)
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }
}
