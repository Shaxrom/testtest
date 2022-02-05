package uz.episodeone.merchants.api.service;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.episodeone.merchants.dto.*;
import uz.episodeone.merchants.helpers.Constants;
import uz.episodeone.merchants.service.MerchantService;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping(Constants.API_BASE_PATH + "/services")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class MerchantEndpoint {

    MerchantService merchantService;

    @PostMapping("/billing/init")
    public ResponseEntity<MerchantServiceDetailsDto> initBilling(@RequestBody InitBillingDto initBillingDto) {
        return ResponseEntity.ok(merchantService.initBilling(initBillingDto));
    }

    @PostMapping("/billing/submit")
    public ResponseEntity<?> submit(@RequestBody SubmitPaymentDto submitPaymentDto) {
        merchantService.submitBilling(submitPaymentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> get(
            @RequestParam(value = "ids",required = false) List<Long> ids,
            @RequestParam(value = "filter",required = false) Filter filter) {
        return ResponseEntity.ok(merchantService.find(ids,filter));
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> create(@RequestBody ServiceDTO merchant) {
        return ResponseEntity.ok(merchantService.create(merchant));
    }

    @PutMapping
    public ResponseEntity<ServiceDTO> update(@RequestBody ServiceDTO entity) {
        return ResponseEntity.ok(merchantService.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        merchantService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/merchants/updates/{date}")
    public ResponseEntity<List<ServiceDTO>> getNewlyUpdateMerchants(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(merchantService.getLastUpdated(date));
    }

}
