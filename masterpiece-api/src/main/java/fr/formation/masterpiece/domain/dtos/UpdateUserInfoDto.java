package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class UpdateUserInfoDto {

    @NotBlank
    private String email;
}
