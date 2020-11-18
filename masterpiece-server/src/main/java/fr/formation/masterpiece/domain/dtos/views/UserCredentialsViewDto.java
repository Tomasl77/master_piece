package fr.formation.masterpiece.domain.dtos.views;

import lombok.Getter;

@Getter
public class UserCredentialsViewDto {

    private Long id;

    private String username;

    /**
     * Empty no-args constructor
     */
    protected UserCredentialsViewDto() {
	//
    }
}
