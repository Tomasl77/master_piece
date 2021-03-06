package fr.formation.masterpiece.domain.dtos.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import fr.formation.masterpiece.commons.annotations.UniqueEmail;

/**
 * {@code DTO} representing informations to update a {@code EntityUser}
 *
 * @author Tomas LOBGEOIS
 *
 */
public class EntityUserPatchDto {

    @UniqueEmail
    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    /**
     * Empty no-args constructor
     */
    protected EntityUserPatchDto() {
    }
}
