package uz.episodeone.merchants.dto.paynet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaynetResponseFieldDTO {
    private String fieldName;
    private String labelRu;
    private String labelUz;
    private int order;
}