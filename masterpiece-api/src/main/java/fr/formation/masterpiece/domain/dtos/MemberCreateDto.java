package fr.formation.masterpiece.domain.dtos;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateDto {

    @Email
    @NotBlank
    private String email;

    @Valid
    private UserAuthDto user;

    public MemberCreateDto() {
    }
}
