package uz.episodeone.merchants.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.episodeone.merchants.dto.CategoryDTO;
import uz.episodeone.merchants.dto.Filter;
import org.springframework.transaction.annotation.Transactional;
import uz.episodeone.merchants.dto.ProviderDTO;


import java.util.List;

public interface CategoryService {
    List<CategoryDTO> find(List<Long> ids, Filter filter);

    @Transactional
    CategoryDTO update(CategoryDTO categoryDTO);

    CategoryDTO create(CategoryDTO entity);

    void delete(Long id);

    Page<CategoryDTO> findAll(Pageable pageable);

    Page<ProviderDTO> findProviders(Long id, Pageable pageable);
}
