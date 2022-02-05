package uz.episodeone.merchants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.episodeone.merchants.domain.generic.Identity;

@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
public class EntityExistsException extends ApplicationException {
    public EntityExistsException(String text) {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), text);
    }
}
