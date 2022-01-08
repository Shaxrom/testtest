package uz.episodeone.merchants.dto.uzcard;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName
public class CardInfoDTO {

    private String id;
    private String username;
    private String pan;
    private String expiry;
    private String status;
    private String phone;
    private String fullName;
    private Long balance;
    private Boolean sms;
    private Long pincnt;
    private String aacct;
    private String cardtype;
    private Long holdAmount;
    private Long cashbackAmount;
}
