package uz.episodeone.merchants.domain;

import lombok.*;
import org.hibernate.Hibernate;
import uz.episodeone.merchants.domain.enums.PaymentInstrument;

import javax.persistence.*;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String description;

    private Boolean enabled;

    @Column(name = "icon_id")
    private String iconId;

    @Column(name = "paynet_category_id")
    private Long paynetCategoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
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
