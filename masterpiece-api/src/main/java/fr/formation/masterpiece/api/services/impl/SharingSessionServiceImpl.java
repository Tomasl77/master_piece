package fr.formation.masterpiece.api.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.api.repositories.SharingSessionRepository;
import fr.formation.masterpiece.api.repositories.SubjectRepository;
import fr.formation.masterpiece.api.repositories.UserProfileRepository;
import fr.formation.masterpiece.api.services.SharingSessionService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.views.SharingSessionViewDto;
import fr.formation.masterpiece.domain.entities.SharingSession;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.domain.entities.UserProfile;
import fr.formation.masterpiece.security.SecurityHelper;

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
    @Transactional
    public void create(SharingSessionCreateDto dto) {
	Long userId = SecurityHelper.getUserId();
	UserProfile user = userProfileRepository
	        .findProfileWithUserCredentialsId(userId)
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "No account found"));
	Subject subject = subjectRepository.getOne(dto.getSubjectId());
	SharingSession session = convert(dto, SharingSession.class);
	session.setUserProfile(user);
	session.setSubject(subject);
	subjectRepository.isScheduled(dto.getSubjectId());
	sharingSessionRepository.save(session);
    }

    @Override
    public List<SharingSessionViewDto> getAllSessions() {
	return convertList(sharingSessionRepository.findAll(),
	        SharingSessionViewDto.class);
    }
}
