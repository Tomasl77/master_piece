package fr.formation.masterpiece.domain.dtos.users;

import lombok.Getter;

/**
 * {@code DTO} representation of the email of a {@code CustomUser}
 *
 * @author Tomas LOBGEOIS
 *
 */
@Getter
public class UpdateCustomUserDto {

    private String email;

    /**
     * Empty no-args constructor
     */
    protected UpdateCustomUserDto() {
	//
    }
}
