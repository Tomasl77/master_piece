package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.views.CategoryViewDto;

public interface CategoryService {

    List<CategoryViewDto> getAll();
}
