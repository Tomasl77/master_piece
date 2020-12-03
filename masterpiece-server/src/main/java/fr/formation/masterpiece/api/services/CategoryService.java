package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.categories.CategoryViewDto;

/**
 * Service to handle {@code Category} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface CategoryService {

    /**
     * Get a {@code List} of all active {@code Category}
     *
     * @return a {@code List} of {@code CategoryViewDto}
     */
    List<CategoryViewDto> getAll();
}
