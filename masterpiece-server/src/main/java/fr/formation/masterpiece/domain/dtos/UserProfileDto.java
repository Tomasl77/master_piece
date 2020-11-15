package fr.formation.masterpiece.domain.dtos;

import lombok.Getter;

@Getter
public class UserProfileDto {

    private String email;

    private UserCredentialsDto credentials;

    /**
     * Empty no-args constructor
     */
    protected UserProfileDto() {
    }
}