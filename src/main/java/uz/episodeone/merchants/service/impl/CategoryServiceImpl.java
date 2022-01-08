package uz.episodeone.merchants.service.impl;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.episodeone.merchants.domain.Category;
import uz.episodeone.merchants.dto.CategoryDTO;
import uz.episodeone.merchants.dto.Filter;
import uz.episodeone.merchants.dto.mapper.CategoryMapper;
import uz.episodeone.merchants.helpers.ErrorCode;
import uz.episodeone.merchants.repository.CategoryDAO;
import uz.episodeone.merchants.service.CategoryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class CategoryServiceImpl implements CategoryService {
    CategoryDAO categoryDAO;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> find(List<Long> ids, Filter filter) {
        List<Category> categories = List.of();
        if (filter == null) {
            categories = categoryDAO.findAll();
        } else if (ids != null ){
            switch (filter) {
                case CATEGORY: categories = categoryDAO.findAllById(ids); break;
                case PROVIDER : categories = categoryDAO.findByProviderId(ids); break;
                case SERVICE : categories = categoryDAO.findByServiceIds(ids); break;
            }
        } else {
            throw new IllegalStateException("Unexpected value");
        }
        return categories.stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDTO update(CategoryDTO categoryDTO) {
        Category category = categoryDAO.findById(categoryDTO.getId()).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.CATEGORY_NOT_FOUND.getValue()));
        categoryMapper.update(categoryDTO, category);
        return categoryMapper.toDto(categoryDAO.save(category));
    }


    @Transactional
    public CategoryDTO create(CategoryDTO categoryDTO) {
        return categoryMapper.toDto(
                    categoryDAO.save(
                        categoryMapper.toEntity(categoryDTO)));
    }


    @Transactional
    public void delete(Long id) {
        categoryDAO.deleteById(id);
    }

}
