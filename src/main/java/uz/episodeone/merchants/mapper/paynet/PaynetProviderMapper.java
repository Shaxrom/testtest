package uz.episodeone.merchants.mapper.paynet;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.episodeone.merchants.domain.Provider;
import uz.episodeone.merchants.dto.paynet.PaynetProviderShortDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring",
        uses = PaynetServiceMapper.class)
public interface PaynetProviderMapper {

    @Mapping(target = "paymentInstrumentProviderId", source = "id")
    @Mapping(target = "id", ignore = true)
    Provider toEntity(PaynetProviderShortDTO paynetProviderShortDTO);
}
