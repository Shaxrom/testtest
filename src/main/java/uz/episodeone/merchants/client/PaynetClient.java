package uz.episodeone.merchants.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import uz.episodeone.merchants.dto.PaymentInstrumentProviderDTO;
import uz.episodeone.merchants.dto.PaymentRequestedFieldDTO;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;

@FeignClient(name = "paynet-adapter", url = "${pp.paynet.baseurl}")
public interface PaynetClient extends PaymentInstrumentClient{
    @GetMapping(name = "/{id}")
    SuccessResponseWrapper<PaymentInstrumentProviderDTO> getProvider(@PathVariable("id") Long paymentInstrumentServiceId);

    @PostMapping(name = "/payment/perform")
    SuccessResponseWrapper<PaymentInstrumentProviderDTO> perform(
            @RequestParam("service_id") Long ServiceId,
            @RequestParam("time") Long time,
            @RequestParam("id") String id,
            @RequestBody PaymentRequestedFieldDTO requestedFieldDTO);
}
