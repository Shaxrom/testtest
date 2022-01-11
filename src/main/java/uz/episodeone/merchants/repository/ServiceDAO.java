package uz.episodeone.merchants.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.episodeone.merchants.domain.Provider;
import uz.episodeone.merchants.domain.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceDAO extends JpaRepository<Service, Long> {


    @EntityGraph(value = "Service.all")
    Page<Service> findByProvider_IdAndActiveTrue(Long categoryId, Pageable pageable);

    @EntityGraph(value = "Service.all")
    List<Service> findByProvider_IdAndActiveTrue(Long providerId);

    @Query("select e from Service e where e.provider in ?1")
    List<Service> findByProviderIn(List<Provider> providers);

    @EntityGraph(value = "Service.categoryAndNames")
    Page<Service> readAllByIdNotNull(Pageable pageable);

    Optional<Service> readById(Long id);

    @Query("select e from Service e where e.updatedAt > dateTime and e.deletedAt = null")
    List<Service> findAllNewlyUpdated(Instant dateTime);

    List<Service> findByProviderIdAndActiveTrue(Long providerId);

    @Query("select s from Service s join s.provider p join p.category c where c.id in ?1 and s.active is true")
    List<Service> findByCategoryIdsAndActiveTrue(List<Long> ids);

    List<Service> findByProviderIdIn(List<Long> ids);

    List<Service> findByProviderId(Long providerId);
}
