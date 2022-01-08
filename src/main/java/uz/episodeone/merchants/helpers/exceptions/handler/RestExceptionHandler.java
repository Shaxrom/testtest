package uz.episodeone.merchants.helpers.exceptions.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.episodeone.merchants.helpers.ErrorCode;
import uz.episodeone.merchants.helpers.exceptions.BadRequestException;
import uz.episodeone.merchants.helpers.exceptions.EntityExistsException;
import uz.episodeone.merchants.helpers.exceptions.UnauthorizedException;
import uz.episodeone.merchants.helpers.utils.JsonUtils;
import uz.episodeone.merchants.helpers.wrapper.ErrorResponseWrapper;
import uz.episodeone.merchants.helpers.wrapper.MessageResponse;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ErrorResponseWrapper<MessageResponse> handleEntityExistsException(EntityNotFoundException ex) {
        MessageResponse messageResponse = new MessageResponse("NOT_FOUND");
        return JsonUtils.errorApiResponse(messageResponse, HttpStatus.NOT_FOUND, ex, ErrorCode.NOT_FOUND).getBody();
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ErrorResponseWrapper<MessageResponse> handleEntityExistsException(EntityExistsException ex) {
        MessageResponse messageResponse = new MessageResponse("ALREADY_EXIST");
        return JsonUtils.errorApiResponse(messageResponse, HttpStatus.FOUND,ex, ErrorCode.ALREADY_EXIST).getBody();
    }

    @ExceptionHandler(BadRequestException.class)
    protected ErrorResponseWrapper<MessageResponse> handleEntityExistsException(BadRequestException ex) {
        MessageResponse messageResponse = new MessageResponse("BAD_REQUEST");
        return JsonUtils.errorApiResponse(messageResponse, BAD_REQUEST, ex, ErrorCode.BAD_REQUEST).getBody();
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ErrorResponseWrapper<MessageResponse> handleEntityExistsException(UnauthorizedException ex) {
        MessageResponse messageResponse = new MessageResponse("UNAUTHORIZED");
        return JsonUtils.errorApiResponse(messageResponse, HttpStatus.UNAUTHORIZED, ex,  ErrorCode.NOT_AUTHORIZED).getBody();
    }
}
