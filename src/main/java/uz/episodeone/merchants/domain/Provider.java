package uz.episodeone.merchants.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import uz.episodeone.merchants.api.sync.dto.ProviderMerchantPoolDTO;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;
import uz.episodeone.merchants.dto.PaymentInstrumentProviderDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "provider",schema = "core")
public class Provider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "name")
    private String name;

    @Column(name = "active", columnDefinition = "boolean default true", nullable = false)
    private Boolean active = true;

    @Column(name = "icon_id")
    private String iconId;

    @Enumerated(EnumType.STRING)
    private PaymentInstrument paymentInstrument;

    @Column(name = "payment-instrument-service-id", insertable = false, updatable = false)
    private Long paymentInstrumentServiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_provider_category_id"))
    private Category category;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Service> services = new HashSet<>();

    public Provider(ProviderMerchantPoolDTO providerMerchantPoolDTO) {
        this.paymentInstrumentServiceId = providerMerchantPoolDTO.getProviderId();
        this.paymentInstrument = PaymentInstrument.PAYNET;
    }

    public void addService(Service service) {
        services.add(service);
        service.setProvider(this);
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
        return id != null && Objects.equals(id, provider.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}