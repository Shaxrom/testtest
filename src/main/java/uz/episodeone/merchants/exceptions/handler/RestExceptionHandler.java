package uz.episodeone.merchants.exceptions.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.episodeone.merchants.dto.error.FailResponse;
import uz.episodeone.merchants.exceptions.ApplicationException;

import java.util.Locale;
import java.util.Objects;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<FailResponse> handleEntityExistsException(ApplicationException ex) {
        String message = ex.getMessage();
        if (Objects.isNull(message) || message.isBlank())
            message = messageSource.getMessage(ex.getCode(), null, Locale.getDefault());

        FailResponse fail = new FailResponse();
        FailResponse.FailData data = new FailResponse.FailData();
        data.setErrorId(ex.getCode());
        data.setMessage(message);
        fail.setData(data);
        return new ResponseEntity<>(fail, ex.getStatus());
    }
}
