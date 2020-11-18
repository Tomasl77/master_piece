package fr.formation.masterpiece.domain.dtos;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import fr.formation.masterpiece.commons.annotations.UniqueEmail;
import lombok.Getter;

@Getter
public class UserProfileCreateDto {

    @UniqueEmail
    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    @Valid
    UserCredentialsCreateDto credentials;

    /**
     * Empty no-args constructor
     */
    protected UserProfileCreateDto() {
	//
    }

    @Override
    public String toString() {
	return "{email: " + email + ", credentials: " + credentials + "}";
    }
}
