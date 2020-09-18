package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoCreateDto {

    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    public UserInfoCreateDto() {
    }
}
