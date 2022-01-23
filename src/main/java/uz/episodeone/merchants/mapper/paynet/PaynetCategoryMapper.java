package uz.episodeone.merchants.mapper.paynet;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.episodeone.merchants.domain.Category;
import uz.episodeone.merchants.dto.paynet.PaynetCategoryShortDTO;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring",
        uses = PaynetProviderMapper.class)
public interface PaynetCategoryMapper {

    @Mapping(target = "paynetCategoryId", source = "id")
    Category toEntity(PaynetCategoryShortDTO paynetCategoryShortDTO);
}
