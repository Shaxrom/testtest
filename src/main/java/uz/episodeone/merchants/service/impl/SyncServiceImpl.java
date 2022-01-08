package uz.episodeone.merchants.service.impl;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import uz.episodeone.merchants.api.sync.dto.MerchantPoolDTO;
import uz.episodeone.merchants.api.sync.dto.ProviderMerchantPoolDTO;
import uz.episodeone.merchants.domain.Provider;
import uz.episodeone.merchants.domain.Service;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;
import uz.episodeone.merchants.helpers.exceptions.BadRequestException;
import uz.episodeone.merchants.repository.CategoryDAO;
import uz.episodeone.merchants.repository.ProviderDAO;
import uz.episodeone.merchants.repository.ServiceDAO;
import uz.episodeone.merchants.service.SyncService;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@org.springframework.stereotype.Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class SyncServiceImpl implements SyncService {
    CategoryDAO categoryDAO;
    ProviderDAO providerDAO;
    ServiceDAO serviceDAO;

    @Override
    public void syncMerchantPool(MerchantPoolDTO merchantPoolDTO) {
        if (merchantPoolDTO.getType().equals(PaymentInstrument.PAYNET)) {

            merchantPoolDTO.getCategoryDTO().forEach(categoryMerchantPool -> {
                Long categoryId = categoryDAO
                        .findByPaynetCategoryId(categoryMerchantPool.getCategoryId())
                        .orElseThrow(() -> new BadRequestException()).getId();

                syncProvider(categoryId, categoryMerchantPool.getProviderDTO());
                syncService(categoryMerchantPool.getProviderDTO());
            });

        }
    }

    private void syncProvider(Long categoryId, List<ProviderMerchantPoolDTO> providerDTOs) {
        List<Long> providerIds = providerDAO
                .findByCategoryId(categoryId)
                .stream()
                .map(Provider::getId)
                .collect(Collectors.toList());

        List<Provider> providers = providerDTOs
                .stream()
                .filter(providerDTO -> !providerIds.contains(providerDTO.getProviderId()))
                .map(Provider::new)
                .collect(Collectors.toList());

        providerDAO.saveAll(providers);
    }

    private void syncService(List<ProviderMerchantPoolDTO> providers) {
        providers.forEach(p -> {
            Long providerId = providerDAO
                    .findByPaymentInstrumentId(p.getProviderId())
                    .orElseThrow(() -> new BadRequestException())
                    .getId();

            List<Long> serviceIds = serviceDAO
                    .findByProviderId(providerId)
                    .stream()
                    .map(Service::getId)
                    .collect(Collectors.toList());

            List<Service> services = p
                    .getServiceIds()
                    .stream()
                    .filter(s -> !serviceIds.contains(s))
                    .map(Service::new)
                    .collect(Collectors.toList());

            serviceDAO.saveAll(services);
        });
    }
}
