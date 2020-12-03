package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import fr.formation.masterpiece.commons.annotations.UniqueEmail;
import lombok.Getter;

@Getter
public class CustomUserPatchDto {

    @UniqueEmail
    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    /**
     * Empty no-args constructor
     */
    protected CustomUserPatchDto() {
    }
}
