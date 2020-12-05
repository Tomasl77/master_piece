package fr.formation.masterpiece.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.api.services.CategoryService;
import fr.formation.masterpiece.commons.annotations.HasRoleUser;
import fr.formation.masterpiece.domain.dtos.categories.CategoryViewDto;

/**
 * a {@code RestController} to handle {@code Category}
 *
 * @author Tomas LOBGEOIS
 *
 */
@RestController
@HasRoleUser
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
	this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryViewDto> getAll() {
	return categoryService.getAll();
    }
}
