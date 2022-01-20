package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PerformPaymentResult {

    String transactionId;
    String status;
    String statusText;
    String time;
    PaymentResponse[] response;
}
