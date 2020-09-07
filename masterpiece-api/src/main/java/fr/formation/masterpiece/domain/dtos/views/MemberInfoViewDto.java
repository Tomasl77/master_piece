package fr.formation.masterpiece.domain.dtos.views;

public interface MemberInfoViewDto {

    Long getId();

    String getEmail();

    UserInfoViewDto getUser();
}
