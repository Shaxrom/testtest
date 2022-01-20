package uz.episodeone.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
public class RequestFieldDTO {
    private String name;
    private String value;

    public RequestFieldDTO(Map.Entry<String, String> x) {
        this.name = x.getKey();
        this.value = x.getValue();
    }
}
