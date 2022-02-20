package uz.episodeone.merchants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderAdminDTO {

    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("address_registry")
    private String addressRegistry;

    @JsonProperty("inn")
    private String inn;

    @JsonProperty("category")
    private String category;

    @JsonProperty("is_active")
    private Boolean isActive;
}
