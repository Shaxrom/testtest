package uz.episodeone.merchants.client;


import uz.episodeone.merchants.dto.PaymentInstrumentProviderDTO;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;

public interface PaynetClient extends PaymentInstrumentClient{
    SuccessResponseWrapper<PaymentInstrumentProviderDTO> getProvider(Long paymentInstrumentServiceId);
}
