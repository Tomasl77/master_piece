package fr.formation.masterpiece.domain.dtos.subjects;

/**
 * {@code DTO} representation of a {@code Subject}.
 * <p>
 * This DTO give all informations about a {@code Subject}.
 *
 * @author Tomas LOBGEOIS
 */
public class SubjectViewDto {

    private String title;

    /**
     * Empty no-args constructor
     */
    protected SubjectViewDto() {
    }

    public String getTitle() {
	return title;
    }
}
