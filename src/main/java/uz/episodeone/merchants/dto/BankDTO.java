package uz.episodeone.merchants.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
//@ApiModel
@JsonTypeName
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankDTO implements Serializable {

//    @ApiParam(value = "Bank ID in GlobalPay")
    @JsonProperty(value = "id")
    private Long id;

//    @ApiParam(value = "Bank Name")
    @JsonProperty(value = "name")
    private String name;

//    @ApiParam(value = "BankC required for Humo requests")
    @JsonProperty(value = "humo_bank_c")
    private String humoBankC;

//    @ApiParam(value = "Uzcard BIN ID")
    @JsonProperty(value = "uzcard_bank_institute_id")
    private String uzcardBankInstituteId;

//    @ApiParam(value = "Receiving amount limit for one transaction")
    @JsonProperty(value = "receiving_amount_limit")
    private Long receivingAmountLimit;

//    @ApiParam(value = "Transfer amount limit for one transaction")
    @JsonProperty(value = "transfer_amount_limit")
    private Long transferAmountLimit;

//    @ApiParam(value = "Receiving count limit by month")
    @JsonProperty(value = "receiving_count_limit")
    private Long receivingCountLimit;

//    @ApiParam(value = "Receiving count limit by month")
    @JsonProperty(value = "transfer_count_limit")
    private Long transferCountLimit;

//    @ApiParam(name = "icon_id")
    @JsonProperty(value = "Bank icon ID")
    private String iconId;

//    @ApiParam(name = "Percent value for P2P from Uzcard to Uzcard cards")
    @JsonProperty(value = "percent_utu")
    private double percentUtu;

//    @ApiParam(name = "Percent value for P2P from Uzcard to Humo cards")
    @JsonProperty(value = "percent_uth")
    private double percentUth;

//    @ApiParam(name = "Percent value for P2P from Humo to Uzcard cards")
    @JsonProperty(value = "percent_htu")
    private double percentHtu;

//    @ApiParam(name = "Percent value for P2P from Humo to Humo cards")
    @JsonProperty(value = "percent_hth")
    private double percentHth;

//    @ApiParam(name = "Uzcard merchant ID")
    @JsonProperty(value = "uzcard_merchant_id")
    private String uzcardMerchantId;

//    @ApiParam(name = "Uzcard terminal ID")
    @JsonProperty(value = "uzcard_terminal_id")
    private String uzcardTerminalId;

//    @ApiParam(name = "Humo merchant ID")
    @JsonProperty(value = "humo_merchant_id")
    private String humoMerchantId;

//    @ApiParam(name = "Humo terminal ID")
    @JsonProperty(value = "humo_terminal_id")
    private String humoTerminalId;
}
