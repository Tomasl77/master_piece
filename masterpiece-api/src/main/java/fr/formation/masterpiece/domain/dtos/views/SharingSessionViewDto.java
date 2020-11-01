package fr.formation.masterpiece.domain.dtos.views;

import java.time.LocalDateTime;

import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.domain.entities.UserProfile;
import lombok.Getter;

@Getter
public class SharingSessionViewDto {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private SubjectViewDto subject;

    private UserProfileViewDto userProfile;
}
