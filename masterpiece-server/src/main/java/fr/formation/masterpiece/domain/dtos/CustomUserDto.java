package fr.formation.masterpiece.domain.dtos;

import lombok.Getter;

@Getter
public class CustomUserDto {

    private String username;

    private String email;

    /**
     * Empty no-args constructor
     */
    protected CustomUserDto() {
	//
    }
}
