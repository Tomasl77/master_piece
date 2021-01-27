package fr.formation.masterpiece.domain.dtos.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import fr.formation.masterpiece.commons.annotations.UniqueEmail;
import fr.formation.masterpiece.commons.annotations.UniqueUser;

/**
 * {@code DTO} representing {@code EntityUser} data to be persisted in database.
 *
 * @author Tomas LOBGEOIS
 */
public class EntityUserCreateDto {

    private final String message = "Must contains at least 8 characters, 1 uppercase, 1 lowercase, 1 digit and 1 special char";

    private final String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,30}$";

    @NotBlank
    @UniqueUser
    @Size(min = 2, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8, max = 30)
    @Pattern(regexp = pattern, message = message)
    private String password;

    @UniqueEmail
    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    /**
     * Empty no-args constructor
     */
    protected EntityUserCreateDto() {
    }

    /**
     * Getter for username
     *
     * @return an username
     */
    public String getUsername() {
	return username;
    }

    /**
     * Getter for password
     *
     * @return a password
     */
    public String getPassword() {
	return password;
    }

    /**
     * Getter for email
     *
     * @return an email
     */
    public String getEmail() {
	return email;
    }
}
