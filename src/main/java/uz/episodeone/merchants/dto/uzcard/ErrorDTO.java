package uz.episodeone.merchants.dto.uzcard;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * JSONRPC error object for UZCARD integration
 */
@Data
@NoArgsConstructor
@JsonTypeName
public class ErrorDTO {

    private Long code;

    private String message;

    private List<DataObject> data;

    @Data
    @NoArgsConstructor
    @JsonTypeName
    private class DataObject {

        private String path;

        private String message;
    }
}
