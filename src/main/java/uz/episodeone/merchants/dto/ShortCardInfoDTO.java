package uz.episodeone.merchants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName
public class ShortCardInfoDTO {
    @JsonProperty("CREF_NO")
    private String crefNo;

    @JsonProperty("EMBOS_NAME")
    private String embosName;

    @JsonProperty("EXP_DT")
    private String expDt;

    @JsonProperty("CARDTYPE")
    private String cardtype;

    @JsonProperty("CARDSTATUS")
    private String cardstatus;
}
