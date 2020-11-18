package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.Email;

import fr.formation.masterpiece.commons.annotations.UniqueEmail;
import lombok.Getter;

@Getter
public class UserProfilePatchDto {

    @Email
    @UniqueEmail
    private String email;

    /**
     * Empty no-args constructor
     */
    protected UserProfilePatchDto() {
    }
}
