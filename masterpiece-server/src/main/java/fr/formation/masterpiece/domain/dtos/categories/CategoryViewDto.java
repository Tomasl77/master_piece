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

    /**
     * Empty no-args constructor
     */
    protected CategoryViewDto() {
    }

    public CategoryViewDto(Long id, String name) {
	this.id = id;
	this.name = name;
    }
}
