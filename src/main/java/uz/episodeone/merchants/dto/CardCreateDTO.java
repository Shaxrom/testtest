package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateDTO {
    private String number;
    private String expiryDate;
    private Long appCardId;
    private String smsNotificationNumber;
}
