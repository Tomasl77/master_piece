package fr.formation.masterpiece.domain.dtos.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileViewDto {

    private Long id;

    private String email;

    UserCredentialsViewDto credentials;
}
