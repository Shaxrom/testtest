package uz.episodeone.merchants.dto.mapper;

import GlobalSolutions.GlobalPay.GPcore.card.domain.Bank;
import GlobalSolutions.GlobalPay.GPcore.dto.BankDTO;
import lombok.NonNull;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface BankMapper {

    @Named("generalEntityToDTO")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "uzcardBankInstituteId", source = "uzcardBankInstituteId")
    @Mapping(target = "humoBankC", source = "humoBankC")
    @Mapping(target = "receivingAmountLimit", source = "receivingAmountLimit")
    @Mapping(target = "transferAmountLimit", source = "transferAmountLimit")
    @Mapping(target = "receivingCountLimit", source = "receivingCountLimit")
    @Mapping(target = "transferCountLimit", source = "transferCountLimit")
    @Mapping(target = "iconId", source = "iconId")
    @Mapping(target = "percentUtu", source = "percentUtu")
    @Mapping(target = "percentUth", source = "percentUth")
    @Mapping(target = "percentHtu", source = "percentHtu")
    @Mapping(target = "percentHth", source = "percentHth")
    @Mapping(target = "uzcardMerchantId", source = "uzcardMerchantId")
    @Mapping(target = "uzcardTerminalId", source = "uzcardTerminalId")
    @Mapping(target = "humoMerchantId", source = "humoMerchantId")
    @Mapping(target = "humoTerminalId", source = "humoTerminalId")
    BankDTO toDTO(@NonNull Bank source);

    @IterableMapping(qualifiedByName = "generalEntityToDTO")
    List<BankDTO> toDTO(List<Bank> all);

    @Named("generalDTOToEntity")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "uzcardBankInstituteId", source = "uzcardBankInstituteId")
    @Mapping(target = "humoBankC", source = "humoBankC")
    @Mapping(target = "receivingAmountLimit", source = "receivingAmountLimit")
    @Mapping(target = "transferAmountLimit", source = "transferAmountLimit")
    @Mapping(target = "receivingCountLimit", source = "receivingCountLimit")
    @Mapping(target = "transferCountLimit", source = "transferCountLimit")
    @Mapping(target = "iconId", source = "iconId")
    @Mapping(target = "percentUtu", source = "percentUtu")
    @Mapping(target = "percentUth", source = "percentUth")
    @Mapping(target = "percentHtu", source = "percentHtu")
    @Mapping(target = "percentHth", source = "percentHth")
    @Mapping(target = "uzcardMerchantId", source = "uzcardMerchantId")
    @Mapping(target = "uzcardTerminalId", source = "uzcardTerminalId")
    @Mapping(target = "humoMerchantId", source = "humoMerchantId")
    @Mapping(target = "humoTerminalId", source = "humoTerminalId")
    Bank toEntity(@NonNull BankDTO entity);
}