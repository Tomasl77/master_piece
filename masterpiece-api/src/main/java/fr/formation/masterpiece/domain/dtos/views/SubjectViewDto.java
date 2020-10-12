package fr.formation.masterpiece.domain.dtos.views;

public interface SubjectViewDto {

    Long getId();

    String getTitle();

    String getDescription();

    String getCategory();

    Long getVote();

    UserProfileViewDto getUser();
}
