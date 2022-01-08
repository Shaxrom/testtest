package uz.episodeone.merchants.service.impl;

import GlobalSolutions.GlobalPay.GPcore.domain.Category;
import GlobalSolutions.GlobalPay.GPcore.dto.CategoryDTO;
import GlobalSolutions.GlobalPay.GPcore.dto.Filter;
import GlobalSolutions.GlobalPay.GPcore.dto.mapper.CategoryMapper;
import GlobalSolutions.GlobalPay.GPcore.helpers.ErrorCode;
import GlobalSolutions.GlobalPay.GPcore.repository.CategoryDAO;
import GlobalSolutions.GlobalPay.GPcore.service.CategoryService;
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