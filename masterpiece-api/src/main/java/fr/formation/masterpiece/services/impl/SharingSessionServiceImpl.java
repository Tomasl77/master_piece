package fr.formation.masterpiece.services.impl;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.AbstractService;
import fr.formation.masterpiece.config.security.SecurityHelper;
import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.entities.SharingSession;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.domain.entities.UserProfile;
import fr.formation.masterpiece.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.repositories.SharingSessionRepository;
import fr.formation.masterpiece.repositories.SubjectRepository;
import fr.formation.masterpiece.repositories.UserProfileRepository;
import fr.formation.masterpiece.services.SharingSessionService;

@Service
public class SharingSessionServiceImpl extends AbstractService
        implements SharingSessionService {

    private final UserProfileRepository userProfileRepository;

    private final SubjectRepository subjectRepository;

    private final SharingSessionRepository sharingSessionRepository;

    public SharingSessionServiceImpl(
            UserProfileRepository userProfileRepository,
            SubjectRepository subjectRepository,
            SharingSessionRepository sharingSessionRepository) {
	this.userProfileRepository = userProfileRepository;
	this.subjectRepository = subjectRepository;
	this.sharingSessionRepository = sharingSessionRepository;
    }

    @Override
    public void create(SharingSessionCreateDto dto) {
	Long userId = SecurityHelper.getUserId();
	Long lecturerId = userProfileRepository
	        .getUserProfileIdByUserId(userId);
	UserProfile userProfile = userProfileRepository.findById(lecturerId)
	        .orElseThrow(() -> new ResourceNotFoundException());
	Subject subject = subjectRepository.findById(dto.getSubjectId())
	        .orElseThrow(() -> new ResourceNotFoundException());
	SharingSession session = convert(dto, SharingSession.class);
	session.setUserProfile(userProfile);
	session.setSubject(subject);
	sharingSessionRepository.save(session);
    }
}
