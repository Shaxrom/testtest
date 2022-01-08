package uz.episodeone.merchants.client;

import uz.episodeone.merchants.dto.PaymentInstrumentServiceDTO;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;

import java.util.List;

public interface PaymentInstrumentClient {
    SuccessResponseWrapper<List<PaymentInstrumentServiceDTO>> getServices (List<Long> ids);
    SuccessResponseWrapper<PaymentInstrumentServiceDTO> getService (Long id);
}
