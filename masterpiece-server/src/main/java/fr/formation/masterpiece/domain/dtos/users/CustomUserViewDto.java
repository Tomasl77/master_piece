package fr.formation.masterpiece.domain.dtos.users;

import lombok.Getter;

@Getter
public class CustomUserViewDto {

    private Long id;

    private String username;

    private String email;

    /**
     * Empty no-args constructor
     */
    protected CustomUserViewDto() {
	//
    }
}
