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
public class PaynetServiceDTO {
    private long id;
    private long minAmount;
    private long maxAmount;
    private String titleRu;
    private String titleUz;
    private long childId;
    private long servicePrice;
    private double agentCommission;
    private double serviceCommission;
    private int order;
    private double serviceCommissionSum;
    private double paynetCommissionSum;
    private List<PaynetRequestedFieldDTO> fields;
    private List<PaynetResponseFieldDTO> responseFields;
    private List<PaynetServiceDTO> services;
}