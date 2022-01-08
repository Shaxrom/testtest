package uz.episodeone.merchants.helpers.exceptions;

import GlobalSolutions.GlobalPay.GPcore.card.domain.generic.Identity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,code = HttpStatus.BAD_REQUEST)
public class EntityExistsException extends javax.persistence.EntityExistsException {
    public EntityExistsException(String text){
        super(text);
    }

    public EntityExistsException(Identity identity){
        super("" + identity.getId());
    }

    public EntityExistsException(Long id){
        super("" + id);
    }
}
