package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import fr.formation.masterpiece.services.UniqueUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSignUpDto {

    @NotBlank
    @UniqueUser(fieldName = "username")
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    public AccountSignUpDto() {
    }
}
