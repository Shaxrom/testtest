package uz.episodeone.merchants.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import uz.episodeone.merchants.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface MerchantService {
    @Transactional(readOnly = true)
    ServiceDTO findOne(Long id);

    @Transactional
    ServiceDTO create(ServiceDTO merchantDto);

    @Transactional
    ServiceDTO update(ServiceDTO merchantDTO);

    @Transactional
    void delete(Long id);

    List<ServiceDTO> find(List<Long> ids, Filter filter);

    List<ServiceDTO> getLastUpdated(LocalDateTime date);

    MerchantServiceDetailsDto initBilling(InitBillingDto initBillingDto);

    void submitBilling(SubmitPaymentDto billingTransactionId);

    Page<ServiceDTO> getServices(Long providerId, Pageable pageable);
}
