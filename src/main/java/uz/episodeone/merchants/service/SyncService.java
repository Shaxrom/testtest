package uz.episodeone.merchants.service;

import uz.episodeone.merchants.api.sync.dto.MerchantPoolDTO;
import uz.episodeone.merchants.dto.paynet.PaynetCategoryShortDTO;

import java.util.List;

public interface SyncService {
    void syncMerchantPool(MerchantPoolDTO merchantPoolDTO);

    void syncPaynet(List<PaynetCategoryShortDTO> categories);
}
