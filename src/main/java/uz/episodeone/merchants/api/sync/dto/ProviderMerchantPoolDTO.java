package uz.episodeone.merchants.api.sync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderMerchantPoolDTO {
    private Long providerId;
    private List<Long> serviceIds;
}
