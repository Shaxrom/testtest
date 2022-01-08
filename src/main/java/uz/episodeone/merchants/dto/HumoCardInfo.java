package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HumoCardInfo {
    private String cardholderID;
    private String cardholderName;
    private String pan;
    private String expiry;
    private String phoneNumber;
    private String status;
}


