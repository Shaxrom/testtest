package uz.episodeone.merchants.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.episodeone.merchants.domain.Provider;
import uz.episodeone.merchants.dto.ProviderAdminDTO;

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
    Page<Provider> findByCategoryId(Long categoryId, Pageable pageable);

    Optional<Provider> findByPaymentInstrumentProviderId(Long providerId);

    @Query(
            "select new uz.episodeone.merchants.dto.ProviderAdminDTO (" +
                    "      p.id, " +
                    "      p.legalName, " +
                    "      p.name, " +
                    "      p.addressRegistry, " +
                    "      p.inn, " +
                    "      p.category.name, " +
                    "      p.active " +
                    " ) " +
                    "from Provider p " +
                    "join p.category c " +
                    "where (:name is null or p.name = :name) and (:categoryId is null or c.id = :categoryId) " +
                    "order by p.id desc"
    )
    Page<ProviderAdminDTO> getAllAdmin(@Param("name") String name,
                                       @Param("categoryId") Long categoryId, Pageable pageable);
}

