package uz.episodeone.merchants.api.sync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MerchantPoolDTO {
    private PaymentInstrument type;
    private List<CategoryMerchantPoolDTO> categoryDTO;
}
