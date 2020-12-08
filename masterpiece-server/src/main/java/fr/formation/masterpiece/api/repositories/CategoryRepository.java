package fr.formation.masterpiece.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.Category;

/**
 * A {@link JpaRepository} to handle {@code Category} persistence.
 *
 * @author Tomas LOBGEOIS
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
