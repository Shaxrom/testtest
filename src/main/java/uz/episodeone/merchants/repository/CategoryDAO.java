package uz.episodeone.merchants.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.episodeone.merchants.domain.Category;
import uz.episodeone.merchants.domain.enums.CategoryType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {

    List<Category> findAllByNameIn(List<String> collect);

    @EntityGraph(value = "Category.names")
    List<Category> readAllByIdNotNullAndEnabledIsTrue();

    @EntityGraph(value = "Category.names")
    Optional<Category> findByTypeAndEnabledIsTrue(CategoryType type);

    @EntityGraph(value = "Category.names")
    List<Category> findByTypeAndEnabledIsFalse(CategoryType type);

    Category findByDifferent(Boolean different);

    List<Category> find(Long id, boolean utility);

    Optional<Category> findByPaynetCategoryId(Long aLong);

    List<Category> findByProviderId(List<Long> ids);

    List<Category> findByServiceIds(List<Long> ids);
}
