package uz.episodeone.merchants.dto;

import GlobalSolutions.GlobalPay.GPcore.domain.enums.ProcessingType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
@NoArgsConstructor
public class CoreCardDTO {

    @ApiModelProperty(value = "Global Pay card ID")
    private Long id;

    @ApiModelProperty(value = "Global Pay card token")
    @NotBlank
    private String token;

    @ApiModelProperty(value = "Masked Card Number")
    @NotBlank
    private String cardNumber;

    @ApiModelProperty(value = "Last time Card balance")
    private Long balance = 0L;

    @ApiModelProperty(value = "Card expiry date")
    @NotBlank
    private String expiryDate;

    @ApiModelProperty(value = "Processing System card token")
    private String externalToken;

    @ApiModelProperty(value = "Phone number that's linked to card")
//    @JsonProperty("sms_notification_number")
    private String smsNotificationNumber;

    @ApiModelProperty(value = "Processing type")
//    @JsonProperty("card_type")
    private ProcessingType type;

    @ApiModelProperty(value = "Application card ID")
//    @JsonProperty("app_card_ai")
    private Long appCardId;

    private Boolean isActive;

    @ApiModelProperty(value = "Card holder name")
//    @JsonProperty("holder_full_name")
    private String holderFullName;

//    @Column(name = "external_status")
    private String externalStatus;

    private BankDTO bankDTO;

}
