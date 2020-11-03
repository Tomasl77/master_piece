package fr.formation.masterpiece.domain.dtos.views;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class SharingSessionViewDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private SubjectViewDto subject;

    private UserProfileViewDto userProfile;
}
