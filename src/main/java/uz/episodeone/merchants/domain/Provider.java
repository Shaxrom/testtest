package uz.episodeone.merchants.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import uz.episodeone.merchants.api.sync.dto.ProviderMerchantPoolDTO;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;
import uz.episodeone.merchants.domain.generic.SoftDeleteModel;
import uz.episodeone.merchants.dto.PaymentInstrumentProviderDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "provider")
public class Provider extends SoftDeleteModel {

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "name")
    private String name;

    @Column(name = "active", columnDefinition = "boolean default true", nullable = false)
    private Boolean active = true;

    @Column(name = "icon_id")
    private String iconId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_instrument")
    private PaymentInstrument paymentInstrument;

    @Column(name = "payment_instrument_provider_id")
    private Long paymentInstrumentProviderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_category_id"))
    private Category category;

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Service> services = new HashSet<>();

    public Provider(ProviderMerchantPoolDTO providerMerchantPoolDTO) {
        this.paymentInstrumentProviderId = providerMerchantPoolDTO.getProviderId();
        this.paymentInstrument = PaymentInstrument.PAYNET;
    }

    public void addService(Set<Service> service) {
        services.addAll(service);
        service.forEach(s -> s.setProvider(this));
    }

    public void addPayInstData(PaymentInstrumentProviderDTO dto) {
        this.legalName = dto.getLegalName();
        this.name = dto.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Provider provider = (Provider) o;
        return getId() != null && Objects.equals(getId(), provider.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Provider update(Provider provider) {
        this.paymentInstrument = PaymentInstrument.PAYNET;
        addService(provider.getServices());
        return this;
    }
}