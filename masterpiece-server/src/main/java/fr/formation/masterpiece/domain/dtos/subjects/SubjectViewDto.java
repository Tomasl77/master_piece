package fr.formation.masterpiece.domain.dtos.subjects;

import fr.formation.masterpiece.domain.dtos.categories.CategoryViewDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;
import lombok.Getter;

/**
 * {@code DTO} representation of a {@code Subject}.
 * <p>
 * This DTO give all informations about a {@code Subject}.
 *
 * @author Tomas LOBGEOIS
 */
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
