package uz.episodeone.merchants.dto.mapper;

import GlobalSolutions.GlobalPay.GPcore.card.domain.Card;
import GlobalSolutions.GlobalPay.GPcore.dto.CoreCardDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring",
        uses = {BankMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CardMapper {

    CoreCardDTO toDTO(Card save);
}
