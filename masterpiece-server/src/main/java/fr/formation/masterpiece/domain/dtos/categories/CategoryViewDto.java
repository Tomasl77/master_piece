package fr.formation.masterpiece.domain.dtos.categories;

/**
 * {@code DTO} representation of a {@code Category}.
 * <p>
 * This DTO give all informations about a {@code Category}.
 *
 * @author Tomas LOBGEOIS
 */
public class CategoryViewDto {

    private Long id;

    private String name;

    public Long getId() {
	return id;
    }

    public String getName() {
	return name;
    }
}
