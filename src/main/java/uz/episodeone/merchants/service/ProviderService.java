package uz.episodeone.merchants.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import uz.episodeone.merchants.dto.Filter;
import uz.episodeone.merchants.dto.ProviderAdminDTO;
import uz.episodeone.merchants.dto.ProviderDTO;
import uz.episodeone.merchants.dto.ServiceDTO;

import java.util.List;

public interface ProviderService {
    @Transactional
    ProviderDTO create(ProviderDTO merchantDto);

    @Transactional
    ProviderDTO update(ProviderDTO merchantDTO);

    @Transactional
    void delete(Long id);

    List<ProviderDTO> find(List<Long> ids, Filter filter);

    Page<ProviderDTO> findAll(Pageable pageable);

    Page<ServiceDTO> findServices(Long id, Pageable pageable);

    Page<ProviderAdminDTO> getProviders(String name, Long categoryId, Pageable pageable);
}
