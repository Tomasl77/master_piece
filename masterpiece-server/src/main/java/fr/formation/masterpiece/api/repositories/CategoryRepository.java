package fr.formation.masterpiece.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.dtos.categories.CategoryViewDto;
import fr.formation.masterpiece.domain.entities.Category;

/**
 * A {@link JpaRepository} to handle {@code Category} persistence.
 *
 * @author Tomas LOBGEOIS
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Return a {@code List} of all {@code Category}
     *
     * @return a {@code List} of {@code Category}
     */
    @Query(JpqlQuery.LIST_CATEGORIES)
    List<CategoryViewDto> getCategories();
}
