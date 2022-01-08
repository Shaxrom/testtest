package uz.episodeone.merchants.service;

import org.springframework.transaction.annotation.Transactional;
import uz.episodeone.merchants.dto.Filter;
import uz.episodeone.merchants.dto.ProviderDTO;

import java.util.List;

public interface ProviderService {
    @Transactional
    ProviderDTO create(ProviderDTO merchantDto);

    @Transactional
    ProviderDTO update(ProviderDTO merchantDTO);

    @Transactional
    void delete(Long id);

    List<ProviderDTO> find(List<Long> ids, Filter filter);

}
