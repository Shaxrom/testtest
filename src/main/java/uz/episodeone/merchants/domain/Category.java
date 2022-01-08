package uz.episodeone.merchants.domain;

import lombok.*;
import org.hibernate.Hibernate;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;
import uz.episodeone.merchants.domain.generic.SoftDeleteModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "category")
public class Category extends SoftDeleteModel {

    private String name;

    private String description;

    private Boolean enabled;

    @Column(name = "icon_id")
    private String iconId;

    @Column(name = "paynet_category_id")
    private Long paynetCategoryId;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Provider> providers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Map<PaymentInstrument, Long> paymentInstrument() {
        EnumMap<PaymentInstrument, Long> enumMap = new EnumMap<>(PaymentInstrument.class);
        enumMap.put(PaymentInstrument.PAYNET,this.paynetCategoryId);
        return enumMap;
    }
}
