package fr.formation.masterpiece.jobs.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountLoginDto {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    public AccountLoginDto() {
    }
}
