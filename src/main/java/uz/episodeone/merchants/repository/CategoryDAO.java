package uz.episodeone.merchants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.episodeone.merchants.domain.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {

    Optional<Category> findByPaynetCategoryId(Long aLong);

    @Query("select c from Category c join c.providers p where p.id in ?1")
    List<Category> findByProviderId(List<Long> ids);

    @Query("select c from Category c " +
        "join c.providers p " +
        "join p.services s " +
        "where s.id in ?1")
    List<Category> findByServiceIds(List<Long> ids);
}
