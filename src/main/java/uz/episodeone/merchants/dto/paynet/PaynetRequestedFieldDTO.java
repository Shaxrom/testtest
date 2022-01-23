package uz.episodeone.merchants.dto.paynet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaynetRequestedFieldDTO {
    private int id;
    private long order;
    private String name;
    private String titleRu;
    private String titleUz;
    private boolean required;
    private boolean readOnly;
    private String fieldType;
    private String isCustomerId;
    private String fieldControl;
    private List<PaynetFilialDTO> fieldValues;
}