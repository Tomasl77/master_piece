package fr.formation.masterpiece.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.api.services.CategoryService;
import fr.formation.masterpiece.commons.annotations.HasRoleUser;
import fr.formation.masterpiece.domain.dtos.categories.CategoryViewDto;

/**
 * A {@link RestController} to handle {@code Category}
 *
 * @author Tomas LOBGEOIS
 *
 */
@RestController
@HasRoleUser
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    @SuppressWarnings("unused")
    private final CacheManager cacheManager;

    private final CategoryService categoryService;

    public CategoryController(CacheManager cacheManager,
            CategoryService categoryService) {
	this.cacheManager = cacheManager;
	this.categoryService = categoryService;
    }

    @Cacheable("categories")
    @GetMapping
    public List<CategoryViewDto> getAll() {
	return categoryService.getAll();
    }
}
