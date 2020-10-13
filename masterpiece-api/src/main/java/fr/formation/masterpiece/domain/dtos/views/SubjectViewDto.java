package fr.formation.masterpiece.domain.dtos.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectViewDto {

    private Long id;

    private String title;

    private String description;

    private String category;

    private Long vote;

    private UserProfileViewDto user;
}
