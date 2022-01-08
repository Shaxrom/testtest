package uz.episodeone.merchants.helpers.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import uz.episodeone.merchants.helpers.ErrorCode;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
//@ApiModel(value = "Объект ответа API")
@JsonPropertyOrder(value = {"status", "code", "message", "timestamp", "data"})
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    //---------------------------------------------- FIELDS ------------------------------------------------------------
    @JsonSerialize(using = StatusSerializer.class)
    @JsonProperty("status")
    protected Status status;

    @JsonProperty("error_code")
    protected ErrorCode code;

    //    @ApiModelProperty("Сообщение об ответе.")
    @JsonProperty("message")
    protected String message;

    //    @ApiModelProperty("Время ответа.")
    @JsonProperty("timestamp")
    protected Instant timestamp;

    //    @ApiModelProperty("Данные.")
    @JsonProperty("data")
    protected T data;

    //    @ApiModelProperty("Дополнительная информация об ошибке")
    @JsonProperty("errors")
    protected List<ErrorObject> errors;

    @JsonProperty("http_status")
    protected HttpStatus httpStatus;

    // ----------------------------------------- CONSTRUCTORS ----------------------------------------------------------

    protected ApiResponse() {
        this.timestamp = Instant.now();
    }

    protected ApiResponse(HttpStatus httpStatus, T data) {
        this();
        this.data = data;
        this.httpStatus = httpStatus;
        setStatus(httpStatus);
    }

    protected ApiResponse(HttpStatus httpStatus, ErrorCode errorCode, T data) {
        this();
        this.data = data;
        this.code = errorCode;
        this.httpStatus = httpStatus;
        setStatus(httpStatus);
    }

    protected ApiResponse(HttpStatus httpStatus, ErrorCode errorCode, T data, String message) {
        this();
        this.message = message;
        this.data = data;
        this.code = errorCode;
        this.httpStatus = httpStatus;
        setStatus(httpStatus);
    }

    protected ApiResponse(HttpStatus httpStatus, ErrorCode errorCode, String message, Throwable ex) {
        this(httpStatus, errorCode, ex);
        this.message = message;
    }

    protected ApiResponse(HttpStatus httpStatus, ErrorCode errorCode, Throwable ex) {
        this();
        this.message = ex.getLocalizedMessage();
        this.code = errorCode;
        this.httpStatus = httpStatus;
        setStatus(httpStatus);
    }

    protected ApiResponse(HttpStatus httpStatus, ErrorCode errorCode, String message) {
        this();
        this.message = message;
        this.code = errorCode;
        this.httpStatus = httpStatus;
        setStatus(httpStatus);
    }


    protected void setStatus(HttpStatus httpStatus) {
        if (httpStatus.is2xxSuccessful() || httpStatus.is1xxInformational()) {
            this.status = Status.SUCCESS;
        }
        if (httpStatus.is4xxClientError() || httpStatus.is5xxServerError()) {
            this.status = Status.ERROR;
        }
    }

    //----------------------------------------------- ADDITIONAL METHODS -----------------------------------------------

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

//    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
//        constraintViolations.forEach(this::addValidationError);
//    }

    protected void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    protected void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

//    protected void addValidationError(ConstraintViolation<?> cv) {
//        this.addValidationError(
//                cv.getRootBeanClass().getSimpleName(),
//                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
//                cv.getInvalidValue(),
//                cv.getMessage());
//    }

    protected void addValidationError(String object, String message) {
        addError(ErrorObject.builder().object(object).message(message).build());
    }

    protected void addError(ErrorObject errorObject) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(errorObject);
    }

    protected void addValidationError(String object, String field, Object rejectedValue, String message) {
        addError(new ErrorObject(object, field, rejectedValue, message));
    }


    //----------------------------------------------- INNER CLASSES ----------------------------------------------------

    protected static class StatusSerializer extends JsonSerializer<Status> {
        @Override
        public void serialize(Status status, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(status.getName());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    protected static class ErrorObject {
        protected String object;
        protected String field;
        protected Object rejectedValue;
        protected String message;
    }

    @Getter
    @RequiredArgsConstructor
    protected enum Status {
        SUCCESS(0, "OK"),
        FAIL(1, "FAIL"),
        ERROR(2, "BAD_GATEWAY");

        protected final int id;
        protected final String name;
    }

}
