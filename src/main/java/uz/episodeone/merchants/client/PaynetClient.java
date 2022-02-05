package uz.episodeone.merchants.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.episodeone.merchants.dto.PaymentInstrumentProviderDTO;
import uz.episodeone.merchants.dto.PaymentRequestedFieldDTO;
import uz.episodeone.merchants.dto.PerformPaymentResult;
import uz.episodeone.merchants.dto.paynet.PaynetCategoryDTO;

import java.util.List;

@FeignClient(name = "paynet-adapter", url = "${pp.paynet.baseurl}")
public interface PaynetClient extends PaymentInstrumentClient{
    @GetMapping(name = "/{id}")
    PaymentInstrumentProviderDTO getProvider(@PathVariable("id") Long paymentInstrumentServiceId);

    @PostMapping("/payment/perform")
    PerformPaymentResult perform(
            @RequestParam("service_id") Long serviceId,
            @RequestParam("time") Long time,
            @RequestParam("id") String id,
            @RequestBody PaymentRequestedFieldDTO requestedFieldDTO);

    @PostMapping("/syncPaynet")
    List<PaynetCategoryDTO> sync();
}
