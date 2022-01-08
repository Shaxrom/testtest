package uz.episodeone.merchants.service.impl;

import GlobalSolutions.GlobalPay.GPcore.client.PaynetClient;
import GlobalSolutions.GlobalPay.GPcore.domain.Provider;
import GlobalSolutions.GlobalPay.GPcore.dto.Filter;
import GlobalSolutions.GlobalPay.GPcore.dto.ProviderDTO;
import GlobalSolutions.GlobalPay.GPcore.dto.mapper.ProviderMapper;
import GlobalSolutions.GlobalPay.GPcore.helpers.ErrorCode;
import GlobalSolutions.GlobalPay.GPcore.repository.ProviderDAO;
import GlobalSolutions.GlobalPay.GPcore.service.ProviderService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class ProviderServiceImpl implements ProviderService {

    ProviderDAO providerDAO;
    ProviderMapper providerMapper;
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
        providers.forEach(provider -> {
                    if (provider.getPaymentInstrumentServiceId() != null) {
                        switch (provider.getPaymentInstrument()) {
                            case PAYNET:
                                provider.addPayInstData(paynetClient.getProvider(provider.getPaymentInstrumentServiceId()).getData());
                                break;
                            //TODO add case for other PaymentInstrumets if they added
                        }
                    }
                });
        return providers.stream().map(providerMapper::toDto).collect(Collectors.toList());
    }
}