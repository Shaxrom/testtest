package uz.episodeone.merchants.dto.uzcard;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JSONRPC response object for UZCARD integration
 */
@Data
@NoArgsConstructor
@JsonTypeName
public class ResponseDTO<T> {

    private String jsonrpc;

    private String id;

    private T result;

    private ErrorDTO error;
}
