package uz.episodeone.merchants.helpers.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uz.episodeone.merchants.helpers.ErrorCode;
import uz.episodeone.merchants.helpers.wrapper.EmptyResponse;
import uz.episodeone.merchants.helpers.wrapper.ErrorResponseWrapper;
import uz.episodeone.merchants.helpers.wrapper.FailResponseWrapper;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;

@UtilityClass
public class JsonUtils {

    public static <T> SuccessResponseWrapper<T> successApiResponse(T data) {
        return new SuccessResponseWrapper<>(HttpStatus.OK, data);
    }

    public static <T> ErrorResponseWrapper<T> errorApiResponse(T data, HttpStatus status, ErrorCode errorCode) {
        return new ErrorResponseWrapper<>(status, data, errorCode);
    }

    public static <T> ResponseEntity<ErrorResponseWrapper<T>> errorApiResponse(T data, HttpStatus status, Throwable thr, ErrorCode errorCode) {
        return new ResponseEntity<>(new ErrorResponseWrapper<>(status, data,thr,errorCode), status);
    }

    public static <T> FailResponseWrapper<T> failApiResponse(T data, HttpStatus status, ErrorCode errorCode) {
        return new FailResponseWrapper<>(status, errorCode, data);
    }

    public static <T> EmptyResponse emptyApiResponse() {
        return new EmptyResponse();
    }
}
