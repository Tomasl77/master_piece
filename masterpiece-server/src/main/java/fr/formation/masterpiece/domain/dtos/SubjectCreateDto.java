package fr.formation.masterpiece.domain.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;

@Getter
public class SubjectCreateDto {

    @NotBlank
    @Length(max = 30)
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String category;

    private LocalDateTime requestDate = LocalDateTime.now();

    /**
     * Empty no-args constructor
     */
    protected SubjectCreateDto() {
    }
}
