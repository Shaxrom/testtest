package uz.episodeone.merchants.api.sync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryMerchantPoolDTO {
    private Long categoryId;
    private List<ProviderMerchantPoolDTO> providerDTO;
}
