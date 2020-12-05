package fr.formation.masterpiece.domain.dtos.users;

import lombok.Getter;

/**
 * {@code DTO} representation of a {@code CustomUser}.
 * <p>
 * This DTO give all informations about a {@code CustomUser}.
 *
 * @author Tomas LOBGEOIS
 */
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
