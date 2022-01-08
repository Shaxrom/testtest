package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.episodeone.merchants.domain.Service;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    private Long id;
    private String legalName;
    private String name;
    private String discount;
    private Long minAmount;
    private Long maxAmount;
    private Long fixedPrice;
    private Boolean active = true;
    private PaymentInstrument paymentInstrument;
    private String iconId;
    private Long categoryId;
    private Set<RequestFieldDTO> requestFields;
    private Set<ResponseFieldDTO> responseFields;
    private Set<FillialDTO> fillials;

    public ServiceDTO(Service service, PaymentInstrumentServiceDTO data) {

    }
}
