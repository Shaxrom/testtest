package uz.episodeone.merchants.helpers.wrapper;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import uz.episodeone.merchants.helpers.ErrorCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FailResponseWrapper<T> extends  ApiResponse<T>{

    public FailResponseWrapper() {
    }

    public FailResponseWrapper(HttpStatus httpStatus, ErrorCode errorCode, T data) {
        super(httpStatus, errorCode, data);
    }

    public FailResponseWrapper(HttpStatus httpStatus, ErrorCode errorCode, T data, String message) {
        super(httpStatus, errorCode, data, message);
    }

    public FailResponseWrapper(HttpStatus httpStatus, ErrorCode errorCode, String message, Throwable ex) {
        super(httpStatus, errorCode, message, ex);
    }

    public FailResponseWrapper(HttpStatus httpStatus, ErrorCode errorCode, Throwable ex) {
        super(httpStatus, errorCode, ex);
    }

    public FailResponseWrapper(HttpStatus httpStatus, ErrorCode errorCode, String message) {
        super(httpStatus, errorCode, message);
    }

    public FailResponseWrapper(HttpStatus status, ErrorCode validationError, ValidationResponse validationResponse) {
    }

    @Override
    public Status getStatus() {
        return Status.FAIL;
    }
}