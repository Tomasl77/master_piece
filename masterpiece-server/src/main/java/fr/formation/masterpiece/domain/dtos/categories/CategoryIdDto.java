package fr.formation.masterpiece.domain.dtos.categories;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CategoryIdDto {

    @NotNull
    private Long id;
}