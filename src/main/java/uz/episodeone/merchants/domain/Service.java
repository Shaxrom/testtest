package uz.episodeone.merchants.domain;

import lombok.*;
import org.hibernate.Hibernate;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;
import uz.episodeone.merchants.domain.generic.SoftDeleteModel;
import uz.episodeone.merchants.dto.PaymentInstrumentServiceDTO;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "service")
public class Service extends SoftDeleteModel {

    @Column(name = "legal_name")
    private String legalName;

    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "discount")
    private String discount;

    @Column(name = "min_amount")
    private Long minAmount;

    @Column(name = "max_amount")
    private Long maxAmount;

    @Column(name = "fixed_price")
    private Long fixedPrice;

    @Column(name = "active")
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_instrument")
    private PaymentInstrument paymentInstrument;

    @Column(name = "icon_id")
    private String iconId;

    @Column(name = "payment_instrument_service_id")
    private Long payInstServiceId;

    @Column(name = "uzcard_merchant_id")
    private String uzcardMerchantId;

    @Column(name = "uzcard_terminal_id")
    private String uzcardTerminalId;

    @Column(name = "humo_merchant_id")
    private String humoMerchantId;

    @Column(name = "humo_terminal_id")
    private String humoTerminalId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "provider_id", foreignKey = @ForeignKey(name = "fk_provider_merchant_id"))
    private Provider provider;

    public Service(Long payInstServiceId) {
        this.payInstServiceId = payInstServiceId;
        this.paymentInstrument = PaymentInstrument.PAYNET;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Service service = (Service) o;
        return getId() != null && Objects.equals(getId(), service.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
