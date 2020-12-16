package fr.formation.masterpiece.domain.dtos.users;

/**
 * {@code DTO} representation of a {@code CustomUser}.
 * <p>
 * This DTO give all informations about a {@code CustomUser}.
 *
 * @author Tomas LOBGEOIS
 */
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

    public Long getId() {
	return id;
    }

    public String getUsername() {
	return username;
    }

    public String getEmail() {
	return email;
    }
}
