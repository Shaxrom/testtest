package uz.episodeone.merchants.dto.uzcard;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName
public class Balance {

  private String id;

  private String pan;

  private Long balance;
}
