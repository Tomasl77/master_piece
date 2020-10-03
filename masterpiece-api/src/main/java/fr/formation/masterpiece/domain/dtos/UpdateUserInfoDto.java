package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateUserInfoDto {

    @NotNull
    private Long id;

    @NotBlank
    private String email;
}
