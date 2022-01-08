package uz.episodeone.merchants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.episodeone.merchants.domain.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {

    Optional<Category> findByPaynetCategoryId(Long aLong);

    List<Category> findByProviderId(List<Long> ids);

    List<Category> findByServiceIds(List<Long> ids);
}
