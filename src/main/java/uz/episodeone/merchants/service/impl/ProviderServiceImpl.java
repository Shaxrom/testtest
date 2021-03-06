package uz.episodeone.merchants.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.episodeone.merchants.client.PaynetClient;
import uz.episodeone.merchants.domain.Provider;
import uz.episodeone.merchants.dto.Filter;
import uz.episodeone.merchants.dto.ProviderAdminDTO;
import uz.episodeone.merchants.dto.ProviderDTO;
import uz.episodeone.merchants.dto.ServiceDTO;
import uz.episodeone.merchants.mapper.ProviderMapper;
import uz.episodeone.merchants.helpers.ErrorCode;
import uz.episodeone.merchants.mapper.ServiceMapper;
import uz.episodeone.merchants.repository.ProviderDAO;
import uz.episodeone.merchants.repository.ServiceDAO;
import uz.episodeone.merchants.service.ProviderService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    ProviderDAO providerDAO;
    ServiceDAO serviceDAO;
    ProviderMapper providerMapper;
    ServiceMapper serviceMapper;
    PaynetClient paynetClient;

    @Override
    @Transactional
    public ProviderDTO create(ProviderDTO merchantDto) {
        return providerMapper.toDto(
                    providerDAO.save(
                            providerMapper.toEntity(merchantDto)));
    }


    @Override
    @Transactional
    public ProviderDTO update(ProviderDTO merchantDTO) {
        Provider providerToUpdate = providerDAO
                .findById(merchantDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SERVICE_ERROR.getValue()));
        providerMapper.update(merchantDTO, providerToUpdate);
        return providerMapper.toDto(providerDAO.save(providerToUpdate));
    }


    @Override
    @Transactional
    public void delete(Long id) {
        providerDAO.deleteById(id);
    }

    public List<ProviderDTO> find(List<Long> ids, Filter filter) {
        List<Provider> providers = List.of();
        if (filter == null) {
            providers = providerDAO.findAll();
        } else if (ids != null) {
            switch (filter) {
                case CATEGORY:
                    providers = providerDAO.findByCategoryIdAndActiveTrue(ids);
                    break;
                case PROVIDER:
                    providers = providerDAO.findAllById(ids);
                    break;
                case SERVICE:
                    providers = providerDAO.findByServiceIds(ids);
                    break;
            }
        } else {
            throw new IllegalStateException("Unexpected value");
        }
        providers.stream()
            .filter(provider -> Objects.nonNull(provider.getPaymentInstrumentProviderId()))
            .filter(provider -> Objects.nonNull(provider.getPaymentInstrument()))
            .forEach(provider -> {
                switch (provider.getPaymentInstrument()) {
                    case PAYNET:
                        Optional.ofNullable(paynetClient.getProvider(provider.getPaymentInstrumentProviderId()))
                            .ifPresent(provider::addPayInstData);
                        break;
                        //TODO add case for other PaymentInstrumets if they added
                }
            });
        return providers.stream().map(providerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<ProviderDTO> findAll(Pageable pageable) {
        return providerDAO.findAll(pageable).map(providerMapper::toDto);
    }

    @Override
    public Page<ServiceDTO> findServices(Long id, Pageable pageable) {
        return serviceDAO.findByProviderId(id, pageable).map(serviceMapper::toDto);
    }

    @Override
    public Page<ProviderAdminDTO> getProviders(String name, Long categoryId, Pageable pageable) {
        return providerDAO.getAllAdmin(name, categoryId, pageable);
    }
}
