package uz.episodeone.merchants.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.episodeone.merchants.domain.Provider;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderDAO extends JpaRepository<Provider, Long> {

    @Query(
            "select distinct p " +
            "from Provider p " +
            "left join p.services ps " +
            "left join p.category pс " +
            "where pс.id = ?1 and ps.active is true and p.active is true")
    Page<Provider> findByCategoryIdAndActiveTrue(Long categoryId, Pageable pageable);

    @Query(
            "select distinct p " +
                    "from Provider p " +
                    "left join p.services ps " +
                    "left join p.category pс " +
                    "where pс.id in ?1 and ps.active is true and p.active is true")
    List<Provider> findByCategoryIdAndActiveTrue(List<Long> categoryIds);

    @Query("select p from Provider p join p.services s where s.id in ?1")
    List<Provider> findByServiceIds(List<Long> ids);

    List<Provider> findByCategoryId(Long categoryId);

    Optional<Provider> findByPaymentInstrumentProviderId(Long providerId);
}

