package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitBillingDto {
    private Long serviceId;
    private String userAccount;
}
