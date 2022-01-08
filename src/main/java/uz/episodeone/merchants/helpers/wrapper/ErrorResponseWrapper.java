package uz.episodeone.merchants.helpers.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import uz.episodeone.merchants.helpers.ErrorCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponseWrapper<T> extends ApiResponse<T> {

    public ErrorResponseWrapper() {
    }

    public ErrorResponseWrapper(HttpStatus httpStatus, T data, ErrorCode code) {
        super(httpStatus, code, data);
        this.code = code;
    }

    public ErrorResponseWrapper(HttpStatus httpStatus, T data, Throwable ex, ErrorCode code) {
        super(httpStatus, code, ex);
        this.data=data;
        this.code = code;
    }

    public ErrorResponseWrapper(HttpStatus httpStatus, Throwable ex, ErrorCode code) {
        super(httpStatus, code, ex);
        this.code = code;
    }

    public ErrorResponseWrapper(HttpStatus httpStatus, String message, ErrorCode code) {
        super(httpStatus, code, message);
        this.code = code;
    }

    public ErrorResponseWrapper(HttpStatus status, MessageResponse messageResponse, HttpMessageNotReadableException ex, ErrorCode jsonMailformed) {
    }

    @Override
    public Status getStatus() {
        return Status.ERROR;
    }

    @JsonProperty
    private ErrorCode code;

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }
}
