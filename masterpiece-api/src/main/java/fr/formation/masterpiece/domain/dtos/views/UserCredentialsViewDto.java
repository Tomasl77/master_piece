package fr.formation.masterpiece.domain.dtos.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentialsViewDto {

    private Long id;

    private String username;

    private boolean isLoggedIn;

    public UserCredentialsViewDto() {
	//
    }
}
