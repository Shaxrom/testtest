package uz.episodeone.merchants.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.episodeone.merchants.domain.Category;
import uz.episodeone.merchants.dto.CategoryDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface CategoryMapper {

  Category toEntity(CategoryDTO paynetCategoryDto);

  CategoryDTO toDto(Category entity);

  void update(CategoryDTO dto, @MappingTarget Category category);
}
