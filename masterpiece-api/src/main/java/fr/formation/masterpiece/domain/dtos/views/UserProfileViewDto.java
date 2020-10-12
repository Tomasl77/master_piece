package fr.formation.masterpiece.domain.dtos.views;

public interface UserProfileViewDto {

    Long getId();

    String getEmail();

    UserCredentialsViewDto getCredentials();
}
