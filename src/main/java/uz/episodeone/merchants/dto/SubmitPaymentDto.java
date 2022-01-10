package uz.episodeone.merchants.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SubmitPaymentDto {
    private String billingTransactionId;
    private Long serviceId;
    private Map<String, String> fields;
}
