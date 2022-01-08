package uz.episodeone.merchants.client;

import GlobalSolutions.GlobalPay.GPcore.dto.PaymentInstrumentProviderDTO;
import GlobalSolutions.GlobalPay.GPcore.helpers.wrapper.SuccessResponseWrapper;

public interface PaynetClient extends PaymentInstrumentClient{
    SuccessResponseWrapper<PaymentInstrumentProviderDTO> getProvider(Long paymentInstrumentServiceId);
}
