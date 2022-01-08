package uz.episodeone.merchants.helpers.wrapper;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Объект с одним сообщением")
@Data
@NoArgsConstructor
public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
