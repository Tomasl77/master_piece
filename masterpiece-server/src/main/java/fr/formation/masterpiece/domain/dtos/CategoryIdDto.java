package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CategoryIdDto {

    @NotNull
    private Long id;
}
