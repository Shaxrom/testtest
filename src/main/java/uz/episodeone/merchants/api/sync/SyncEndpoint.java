package uz.episodeone.merchants.api.sync;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.episodeone.merchants.api.sync.dto.MerchantPoolDTO;
import uz.episodeone.merchants.dto.paynet.PaynetCategoryDTO;
import uz.episodeone.merchants.dto.paynet.PaynetCategoryShortDTO;
import uz.episodeone.merchants.helpers.Constants;
import uz.episodeone.merchants.service.SyncService;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;
import static uz.episodeone.merchants.helpers.utils.JsonUtils.successApiResponse;

@Slf4j
@RestController
@RequestMapping(Constants.API_BASE_PATH + "/sync")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class SyncEndpoint {

    SyncService syncService;

    @PostMapping("/merchants-pool")
    public void syncMerchantPool(@RequestBody MerchantPoolDTO merchantPoolDTO) {
        syncService.syncMerchantPool(merchantPoolDTO);
    }

    @PostMapping("/paynet")
    public void sync(@RequestBody List<PaynetCategoryShortDTO> categories) {
        syncService.syncPaynet(categories);
    }
}
