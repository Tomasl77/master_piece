package fr.formation.masterpiece.domain.dtos.users;

import lombok.Getter;

/**
 * {@code DTO} representation of the username and email of a {@code CustomUser}
 *
 * @author Tomas LOBGEOIS
 *
 */
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
