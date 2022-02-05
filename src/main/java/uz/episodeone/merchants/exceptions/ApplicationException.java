package uz.episodeone.merchants.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final String message;

    public ApplicationException(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
