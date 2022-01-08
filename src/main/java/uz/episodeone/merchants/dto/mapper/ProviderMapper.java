package uz.episodeone.merchants.dto.mapper;

import GlobalSolutions.GlobalPay.GPcore.domain.Provider;
import GlobalSolutions.GlobalPay.GPcore.dto.ProviderDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = {ServiceMapper.class}, componentModel = "spring")
public interface ProviderMapper {

    @Named("general")
    ProviderDTO toDto(Provider provider);


    @IterableMapping(qualifiedByName = "general")
    List<ProviderDTO> toDto(List<Provider> providers);


    void update(ProviderDTO dto, @MappingTarget Provider provider);

    Provider toEntity(ProviderDTO merchantDto);
}
