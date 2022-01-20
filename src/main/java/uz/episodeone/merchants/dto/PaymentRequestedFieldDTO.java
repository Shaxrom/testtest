package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestedFieldDTO {
    private List<RequestFieldDTO> requestedField;

}
