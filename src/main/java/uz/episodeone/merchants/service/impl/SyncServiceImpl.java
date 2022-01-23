package uz.episodeone.merchants.service.impl;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import uz.episodeone.merchants.api.sync.dto.MerchantPoolDTO;
import uz.episodeone.merchants.api.sync.dto.ProviderMerchantPoolDTO;
import uz.episodeone.merchants.domain.Category;
import uz.episodeone.merchants.domain.Provider;
import uz.episodeone.merchants.domain.Service;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;
import uz.episodeone.merchants.dto.paynet.PaynetCategoryShortDTO;
import uz.episodeone.merchants.helpers.exceptions.BadRequestException;
import uz.episodeone.merchants.mapper.paynet.PaynetCategoryMapper;
import uz.episodeone.merchants.repository.CategoryDAO;
import uz.episodeone.merchants.repository.ProviderDAO;
import uz.episodeone.merchants.repository.ServiceDAO;
import uz.episodeone.merchants.service.SyncService;

import java.util.List;
import java.util.Optional;
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
    PaynetCategoryMapper paynetCategoryMapper;

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

    @Override
    public void syncPaynet(List<PaynetCategoryShortDTO> categories) {
        categories.stream()
                .map(paynetCategoryMapper::toEntity)
                .peek(category -> {

                    var categorySaveOrUpdate = categorySaveOrUpdate(category);

                    Optional.of(categorySaveOrUpdate)
                            .map(Category::getProviders)
                            .ifPresent(w -> w.forEach(provider -> {

                                var providerSaveOrUpdate = providerSaveOrUpdate(provider, categorySaveOrUpdate);

                                Optional.of(providerSaveOrUpdate)
                                        .map(Provider::getServices)
                                        .ifPresent(q -> q.forEach(service -> serviceSaveOrUpdate(service, providerSaveOrUpdate)));
                            }));
                })
                .map(categoryDAO::save)
                .collect(Collectors.toList());
    }

    private Category categorySaveOrUpdate(Category category) {
        var ref = new Object() {
            Category categoryRef;
        };

        categoryDAO.findByPaynetCategoryId(category.getPaynetCategoryId()).ifPresentOrElse(
                c -> ref.categoryRef = c.update(category),
                () -> ref.categoryRef = category
        );

        return ref.categoryRef;
    }
    private Provider providerSaveOrUpdate(Provider provider, Category category) {
        var ref = new Object() {
            Provider value;
        };

        providerDAO.findByPaymentInstrumentProviderId(provider.getPaymentInstrumentProviderId()).ifPresentOrElse(
                c -> ref.value = c.update(provider),
                () -> ref.value = provider
        );
        ref.value.setCategory(category);
        return ref.value;
    }
    private Service serviceSaveOrUpdate(Service service, Provider provider) {
        var ref = new Object() {
            Service value;
        };

        serviceDAO.findByPayInstServiceId(service.getPayInstServiceId()).ifPresentOrElse(
                c -> ref.value = c.update(service),
                () -> ref.value = service
        );
        ref.value.setProvider(provider);
        return ref.value;
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
                    .findByPaymentInstrumentProviderId(p.getProviderId())
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
