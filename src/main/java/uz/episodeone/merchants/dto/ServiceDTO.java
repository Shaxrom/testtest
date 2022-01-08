package uz.episodeone.merchants.dto;

import GlobalSolutions.GlobalPay.GPcore.domain.enums.PaymentInstrument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
