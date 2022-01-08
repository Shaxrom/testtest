package uz.episodeone.merchants.api.provider;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.episodeone.merchants.dto.Filter;
import uz.episodeone.merchants.dto.ProviderDTO;
import uz.episodeone.merchants.helpers.Constants;
import uz.episodeone.merchants.helpers.wrapper.EmptyResponse;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;
import uz.episodeone.merchants.service.ProviderService;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;
import static uz.episodeone.merchants.helpers.utils.JsonUtils.emptyApiResponse;
import static uz.episodeone.merchants.helpers.utils.JsonUtils.successApiResponse;

@Slf4j
@RestController
@RequestMapping(Constants.API_BASE_PATH + "/provider")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class ProviderEndpoint {

    ProviderService providerService;

    @GetMapping
    public SuccessResponseWrapper<List<ProviderDTO>> getAll(
            @RequestParam(value = "ids",required = false) List<Long> ids,
            @RequestParam(value = "filter",required = false) Filter filter) {
        return successApiResponse(providerService.find(ids,filter));
    }

    @PostMapping
    public SuccessResponseWrapper<ProviderDTO> create(
            @RequestBody ProviderDTO providerDTO) {
        return successApiResponse(providerService.create(providerDTO));
    }

    @PutMapping
    public SuccessResponseWrapper<ProviderDTO> update(
            @RequestBody ProviderDTO entity) {
        return successApiResponse(providerService.update(entity));
    }

    @DeleteMapping("/{id}")
    public EmptyResponse delete(
            @PathVariable("id") long id) {
        providerService.delete(id);
        return emptyApiResponse();
    }
}
