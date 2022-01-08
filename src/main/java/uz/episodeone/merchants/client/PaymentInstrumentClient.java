package uz.episodeone.merchants.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uz.episodeone.merchants.dto.PaymentInstrumentServiceDTO;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;

import java.util.List;

public interface PaymentInstrumentClient {
    @PostMapping
    SuccessResponseWrapper<List<PaymentInstrumentServiceDTO>> getServices (List<Long> ids);
    @GetMapping
    SuccessResponseWrapper<PaymentInstrumentServiceDTO> getService (Long id);
}
