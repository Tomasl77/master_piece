package fr.formation.masterpiece.domain.dtos.categories;

import lombok.Getter;

/**
 * {@code DTO} representation of a {@code Category}.
 * <p>
 * This DTO give all informations about a {@code Category}.
 *
 * @author Tomas LOBGEOIS
 */
@Getter
public class CategoryViewDto {

    private Long id;

    private String name;
}
