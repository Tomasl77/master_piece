package fr.formation.masterpiece.domain.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectCreateDto {

    @NotBlank
    @Length(max = 20)
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String category;

    private LocalDateTime requestDate = LocalDateTime.now();
}
