package uz.episodeone.merchants.service;

import uz.episodeone.merchants.dto.CategoryDTO;
import uz.episodeone.merchants.dto.Filter;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface CategoryService {
    List<CategoryDTO> find(List<Long> ids, Filter filter);

    @Transactional
    CategoryDTO update(CategoryDTO categoryDTO);

    CategoryDTO create(CategoryDTO entity);

    void delete(Long id);
}