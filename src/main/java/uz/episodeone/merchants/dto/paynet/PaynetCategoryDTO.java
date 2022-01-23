package uz.episodeone.merchants.dto.paynet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaynetCategoryDTO {
    private long id;
    private String titleRu;
    private String titleUz;
    private long order;
    private List<PaynetProviderDTO> providers;
    private Date lastUpdatedAt;
}