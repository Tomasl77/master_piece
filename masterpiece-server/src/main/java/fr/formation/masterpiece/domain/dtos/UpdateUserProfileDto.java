package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class UpdateUserProfileDto {

    @NotBlank
    private String email;

    /**
     * Empty no-args constructor
     */
    protected UpdateUserProfileDto() {
	//
    }
}
