package fr.formation.masterpiece.domain.dtos;

import lombok.Getter;

@Getter
public class UserProfilePatchDto {

    private String email;

    /**
     * Empty no-args constructor
     */
    protected UserProfilePatchDto() {
    }
}
