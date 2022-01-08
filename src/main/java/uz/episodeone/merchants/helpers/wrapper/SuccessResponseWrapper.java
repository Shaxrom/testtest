package uz.episodeone.merchants.helpers.wrapper;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class SuccessResponseWrapper<T> extends ApiResponse<T> {

    public SuccessResponseWrapper() {
        super();
    }

    public SuccessResponseWrapper(HttpStatus httpStatus, T data) {
        super(httpStatus, data);
    }

    @Override
    public Status getStatus() {
        return Status.SUCCESS;
    }
}