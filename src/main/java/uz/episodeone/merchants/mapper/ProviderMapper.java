package uz.episodeone.merchants.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import uz.episodeone.merchants.domain.Provider;
import uz.episodeone.merchants.dto.ProviderDTO;

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
