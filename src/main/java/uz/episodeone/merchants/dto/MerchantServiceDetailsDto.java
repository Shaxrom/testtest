package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantServiceDetailsDto {
    private String uzcardMerchantId;
    private String uzcardTerminalId;
    private String humoMerchantId;
    private String humoTerminalId;
    private String billingTransactionId;
}
