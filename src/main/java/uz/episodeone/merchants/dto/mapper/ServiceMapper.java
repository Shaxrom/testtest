package uz.episodeone.merchants.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.episodeone.merchants.domain.Service;
import uz.episodeone.merchants.dto.PaymentInstrumentServiceDTO;
import uz.episodeone.merchants.dto.ServiceDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    Service toEntity(ServiceDTO serviceDTO);

    void update(ServiceDTO serviceDTO, @MappingTarget Service serviceToUpdate);


    ServiceDTO toDto(Service save);

    List<ServiceDTO> toDto(List<PaymentInstrumentServiceDTO> paymentInstrumentServiceDTOS);

    ServiceDTO toDto(PaymentInstrumentServiceDTO paymentInstrumentServiceDTO);
}