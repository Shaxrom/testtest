package uz.episodeone.merchants.api;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.episodeone.merchants.dto.InitBillingDto;
import uz.episodeone.merchants.dto.MerchantServiceDetailsDto;
import uz.episodeone.merchants.dto.SubmitPaymentDto;
import uz.episodeone.merchants.helpers.Constants;
import uz.episodeone.merchants.helpers.wrapper.EmptyResponse;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;
import uz.episodeone.merchants.service.MerchantService;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;
import static uz.episodeone.merchants.helpers.utils.JsonUtils.emptyApiResponse;
import static uz.episodeone.merchants.helpers.utils.JsonUtils.successApiResponse;

@Slf4j
@RestController
@RequestMapping(Constants.API_BASE_PATH + "/payments")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class BillingController {

    MerchantService merchantService;

    @PostMapping("/init")
    public SuccessResponseWrapper<MerchantServiceDetailsDto> initBilling(
        @RequestBody InitBillingDto initBillingDto) {
        return successApiResponse(merchantService.initBilling(initBillingDto));
    }

    @PostMapping("/submit")
    public EmptyResponse submitPayment(@RequestBody SubmitPaymentDto submitPaymentDto) {
        merchantService.findOne(submitPaymentDto.getServiceId());
        return emptyApiResponse();
    }
}
