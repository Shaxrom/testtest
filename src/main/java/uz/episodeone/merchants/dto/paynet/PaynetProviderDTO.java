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
public class PaynetProviderDTO {
    private long id;
    private String title;
    private String titleShort;
    private List<PaynetServiceDTO> services;
}