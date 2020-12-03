package fr.formation.masterpiece.api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.api.repositories.CategoryRepository;
import fr.formation.masterpiece.api.services.CategoryService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.domain.dtos.views.CategoryViewDto;

/**
 * Default concrete implementation of {@code CategoryService}
 *
 * @author Tomas LOBGEOIS
 *
 */
@Service
public class CategoryServiceImpl extends AbstractService
        implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
	this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryViewDto> getAll() {
	return convertList(categoryRepository.findAll(), CategoryViewDto.class);
    }
}
