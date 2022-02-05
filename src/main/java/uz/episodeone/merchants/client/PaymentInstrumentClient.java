package uz.episodeone.merchants.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uz.episodeone.merchants.dto.PaymentInstrumentServiceDTO;

import java.util.List;

public interface PaymentInstrumentClient {

    @PostMapping
    List<PaymentInstrumentServiceDTO> getServices(List<Long> ids);

    @GetMapping
    PaymentInstrumentServiceDTO getService(Long id);
}
