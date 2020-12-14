package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.NotNull;

import lombok.Getter;

/**
 * {@code DTO} representing the {@code Category}'s id selected when a new
 * {@code Subject} is created
 *
 * @author Tomas LOBGEOIS
 *
 */
@Getter
public class IdDto {

    @NotNull
    private Long id;
}