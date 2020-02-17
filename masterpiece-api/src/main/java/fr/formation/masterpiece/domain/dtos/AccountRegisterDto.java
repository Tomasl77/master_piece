package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import fr.formation.masterpiece.annotations.UniqueUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRegisterDto {

    @NotBlank
    @UniqueUser
    @Size(min = 2, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;

    public AccountRegisterDto() {
    }
}
