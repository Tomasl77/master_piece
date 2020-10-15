package fr.formation.masterpiece.domain.dtos;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileCreateDto {

    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    @Valid
    UserCredentialsCreateDto credentials;

    public UserProfileCreateDto() {
    }

    @Override
    public String toString() {
	return "{email: " + email + " , credentials = " + credentials + "}";
    }
}
