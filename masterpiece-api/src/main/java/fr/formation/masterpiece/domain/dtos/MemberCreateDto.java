package fr.formation.masterpiece.domain.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import fr.formation.masterpiece.annotations.UniqueUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateDto {

    private final String message = "Must contains at least 8 characters, 1 uppercase, 1 lowercase, 1 digit and 1 special char";

    private final String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,30}$";

    @NotBlank
    @UniqueUser
    @Size(min = 2, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8, max = 30)
    @Pattern(regexp = pattern, message = message)
    private String password;

    public MemberCreateDto() {
    }
}
