package fr.formation.masterpiece.domain.dtos.subjects;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import fr.formation.masterpiece.domain.dtos.IdDto;
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

    @Valid
    private IdDto category;

    private LocalDateTime requestDate = LocalDateTime.now();

    /**
     * Empty no-args constructor
     */
    protected SubjectCreateDto() {
    }
}
