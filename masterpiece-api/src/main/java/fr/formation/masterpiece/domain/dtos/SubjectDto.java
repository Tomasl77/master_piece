package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import fr.formation.masterpiece.domain.entities.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {

    @NotBlank
    @Length(max = 20)
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Category category;
}
