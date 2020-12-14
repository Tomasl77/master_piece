package fr.formation.masterpiece.domain.dtos.subjects;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;

/**
 * {@code DTO} representing {@code Subject} data to be persisted in database.
 *
 * @author Tomas LOBGEOIS
 */
@Getter
public class SubjectCreateDto {

    @NotBlank
    @Length(max = 30)
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Long categoryId;

    private LocalDateTime requestDate = LocalDateTime.now();

    /**
     * Empty no-args constructor
     */
    protected SubjectCreateDto() {
    }
}
