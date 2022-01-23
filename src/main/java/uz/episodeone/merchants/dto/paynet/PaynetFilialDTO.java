package uz.episodeone.merchants.dto.paynet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaynetFilialDTO {

    private Long id;
    private String titleRu;
    private String titleUz;
}