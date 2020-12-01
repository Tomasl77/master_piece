package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.views.CategoryViewDto;

/**
 * Service to handle {@code Category} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface CategoryService {

    List<CategoryViewDto> getAll();
}
