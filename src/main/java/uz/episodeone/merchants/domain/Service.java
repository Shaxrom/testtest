package uz.episodeone.merchants.domain;

import lombok.*;
import org.hibernate.Hibernate;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;
import uz.episodeone.merchants.domain.generic.SoftDeleteModel;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "service", schema = "core")
public class Service extends SoftDeleteModel {

//    @Column(name = "legal_name")
//    private String legalName;
//
//    @EqualsAndHashCode.Include
//    private String name;
//
//    @Column(name = "discount")
//    private String discount;
//
//    @Column(name = "min_amount")
//    private Long minAmount;
//
//    @Column(name = "max_amount")
//    private Long maxAmount;
//
//    @Column(name = "fixed_price")
//    private Long fixedPrice;
//
//    @Column(name = "active", columnDefinition = "boolean default true", nullable = false)
//    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    private PaymentInstrument paymentInstrument;

//    @Column(name = "icon_id")
//    @ApiModelProperty(value = "ID картинка мерчанта")
//    private String iconId;
//
    @Column(name = "payment-instrument-service-id", insertable = false, updatable = false)
    private Long payInstServiceId;

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
