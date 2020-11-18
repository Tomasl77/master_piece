package fr.formation.masterpiece.domain.dtos.views;

import lombok.Getter;

@Getter
public class UserProfileViewDto {

    private Long id;

    private String email;

    UserCredentialsViewDto credentials;

    /**
     * Empty no-args constructor
     */
    protected UserProfileViewDto() {
	//
    }
}
