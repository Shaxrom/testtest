package uz.episodeone.merchants.dto.mapper;

import GlobalSolutions.GlobalPay.GPcore.domain.Category;
import GlobalSolutions.GlobalPay.GPcore.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface CategoryMapper {

  Category toEntity(CategoryDTO paynetCategoryDto);

  CategoryDTO toDto(Category entity);

  void update(CategoryDTO dto, @MappingTarget Category category);
}
