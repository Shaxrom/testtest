package uz.episodeone.merchants.mapper.paynet;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.episodeone.merchants.domain.Service;
import uz.episodeone.merchants.dto.paynet.PaynetServiceShortDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface PaynetServiceMapper {

    @Mapping(target = "payInstServiceId", source = "id")
    @Mapping(target = "id", ignore = true)
    Service toEntity(PaynetServiceShortDTO paynetServiceShortDTO);
}
