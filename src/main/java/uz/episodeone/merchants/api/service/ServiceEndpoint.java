package uz.episodeone.merchants.api.service;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import uz.episodeone.merchants.dto.Filter;
import uz.episodeone.merchants.dto.ServiceDTO;
import uz.episodeone.merchants.helpers.Constants;
import uz.episodeone.merchants.helpers.wrapper.EmptyResponse;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;
import uz.episodeone.merchants.service.ProviderServicesService;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;
import static uz.episodeone.merchants.helpers.utils.JsonUtils.emptyApiResponse;
import static uz.episodeone.merchants.helpers.utils.JsonUtils.successApiResponse;

@Slf4j
@RestController
@RequestMapping(Constants.API_BASE_PATH + "/services")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class ServiceEndpoint {

    ProviderServicesService merchantService;

    @GetMapping
    public SuccessResponseWrapper<List<ServiceDTO>> get(
            @RequestParam(value = "ids",required = false) List<Long> ids,
            @RequestParam(value = "filter",required = false) Filter filter) {
        return successApiResponse(merchantService.find(ids,filter));
    }

    @PostMapping
    public SuccessResponseWrapper<ServiceDTO> create(@RequestBody ServiceDTO merchant) {
        return successApiResponse(merchantService.create(merchant));
    }

    @PutMapping
    public SuccessResponseWrapper<ServiceDTO> update(
            @RequestBody ServiceDTO entity) {
        return successApiResponse(merchantService.update(entity));
    }

    @DeleteMapping("/{id}")
    public EmptyResponse delete(@PathVariable("id") long id) {
        merchantService.delete(id);
        return emptyApiResponse();
    }

    @GetMapping("/merchants/updates/{date}")
    public SuccessResponseWrapper<List<ServiceDTO>> getNewlyUpdateMerchants(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return successApiResponse(merchantService.getLastUpdated(date));
    }

}
