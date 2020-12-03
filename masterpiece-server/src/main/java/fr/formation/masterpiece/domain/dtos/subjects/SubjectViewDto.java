package fr.formation.masterpiece.domain.dtos.subjects;

import fr.formation.masterpiece.domain.dtos.categories.CategoryViewDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;
import lombok.Getter;

@Getter
public class SubjectViewDto {

    private Long id;

    private String title;

    private String description;

    private CategoryViewDto category;

    private CustomUserViewDto requester;

    /**
     * Empty no-args constructor
     */
    protected SubjectViewDto() {
	//
    }
}
