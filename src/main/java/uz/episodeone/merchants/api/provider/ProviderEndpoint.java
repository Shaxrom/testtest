package uz.episodeone.merchants.api.provider;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.episodeone.merchants.dto.Filter;
import uz.episodeone.merchants.dto.ProviderDTO;
import uz.episodeone.merchants.dto.ServiceDTO;
import uz.episodeone.merchants.helpers.Constants;
import uz.episodeone.merchants.service.ProviderService;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping(Constants.API_BASE_PATH + "/providers")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class ProviderEndpoint {

    ProviderService providerService;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> getAll(
            @RequestParam(value = "ids",required = false) List<Long> ids,
            @RequestParam(value = "filter",required = false) Filter filter) {
        return ResponseEntity.ok(providerService.find(ids,filter));
    }

    @GetMapping("/{id}/services")
    public ResponseEntity<Page<ServiceDTO>> getServices(@PathVariable("id") Long id,
                                                        Pageable pageable) {
        return ResponseEntity.ok(providerService.findServices(id, pageable));
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> create(
            @RequestBody ProviderDTO providerDTO) {
        return ResponseEntity.ok(providerService.create(providerDTO));
    }

    @PutMapping
    public ResponseEntity<ProviderDTO> update(
            @RequestBody ProviderDTO entity) {
        return ResponseEntity.ok(providerService.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") long id) {
        providerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
