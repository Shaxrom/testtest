package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentResponse {
    String key;
    String labelRu;
    String labelUz;
    String value;
}
