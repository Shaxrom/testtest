package uz.episodeone.merchants.service;

import uz.episodeone.merchants.api.sync.dto.MerchantPoolDTO;

public interface SyncService {
    void syncMerchantPool(MerchantPoolDTO merchantPoolDTO);
}
