package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInstrumentServiceDTO {
    private Long coreServiceId;
    private String legalName;
    private String name;
    private String discount;
    private Long minAmount;
    private Long maxAmount;
    private Long fixedPrice;
    private Boolean active = true;
    private String iconId;
    private Set<RequestFieldDTO> requestFields;
    private Set<ResponseFieldDTO> responseFields;
    private Set<FillialDTO> fillials;
}
