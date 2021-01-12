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

    public CustomUserViewDto(Long id, String username, String email) {
	this.id = id;
	this.username = username;
	this.email = email;
    }

    public String getUsername() {
	return username;
    }

    @Override
    public String toString() {
	return "{id: " + id + ", username: " + username + ", email: " + email
	        + "}";
    }
}
