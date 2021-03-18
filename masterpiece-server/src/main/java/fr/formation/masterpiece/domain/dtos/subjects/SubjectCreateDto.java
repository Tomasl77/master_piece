package fr.formation.masterpiece.domain.dtos.subjects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

/**
 * {@code DTO} representing {@code Subject} data to be persisted in database.
 *
 * @author Tomas LOBGEOIS
 */
public class SubjectCreateDto {

    @NotBlank
    @Length(max = 30)
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Long categoryId;

    /**
     * Empty protected no-args constructor
     */
    protected SubjectCreateDto() {
    }

    public Long getCategoryId() {
	return categoryId;
    }
}
