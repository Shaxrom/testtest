package uz.episodeone.merchants.client;

import GlobalSolutions.GlobalPay.GPcore.dto.PaymentInstrumentServiceDTO;
import GlobalSolutions.GlobalPay.GPcore.helpers.wrapper.SuccessResponseWrapper;

import java.util.List;

public interface PaymentInstrumentClient {
    SuccessResponseWrapper<List<PaymentInstrumentServiceDTO>> getServices (List<Long> ids);
    SuccessResponseWrapper<PaymentInstrumentServiceDTO> getService (Long id);
}
