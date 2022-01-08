package uz.episodeone.merchants.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.episodeone.merchants.dto.PaymentInstrumentProviderDTO;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;

@FeignClient(name = "paynet-adapter",
    url = "${pp.paynet.baseurl}",
    path = "/v1/humo")
public interface PaynetClient extends PaymentInstrumentClient{
    @GetMapping(name = "/{id}")
    SuccessResponseWrapper<PaymentInstrumentProviderDTO> getProvider(@PathVariable("id") Long paymentInstrumentServiceId);
}
