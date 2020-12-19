package fr.formation.masterpiece.api.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.masterpiece.api.repositories.CustomUserRepository;
import fr.formation.masterpiece.api.repositories.SharingSessionRepository;
import fr.formation.masterpiece.api.repositories.SubjectRepository;
import fr.formation.masterpiece.api.services.SharingSessionService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionViewDto;
import fr.formation.masterpiece.domain.entities.CustomUser;
import fr.formation.masterpiece.domain.entities.SharingSession;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.security.SecurityHelper;

/**
 * Default concrete implementation of {@link SharingSessionService}
 *
 * @author Tomas LOBGEOIS
 *
 */
@Service
public class SharingSessionServiceImpl extends AbstractService
        implements SharingSessionService {

    private final CustomUserRepository userRepository;

    private final SubjectRepository subjectRepository;

    private final SharingSessionRepository sharingSessionRepository;

    public SharingSessionServiceImpl(CustomUserRepository userRepository,
            SubjectRepository subjectRepository,
            SharingSessionRepository sharingSessionRepository) {
	this.userRepository = userRepository;
	this.subjectRepository = subjectRepository;
	this.sharingSessionRepository = sharingSessionRepository;
    }

    @Override
    @Transactional
    public SharingSessionViewDto create(SharingSessionCreateDto dto) {
	Long userId = SecurityHelper.getUserId();
	CustomUser user = userRepository.findById(userId)
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "User id: " + userId + " doesn't exist"));
	Subject subject = subjectRepository.getOne(dto.getSubjectId());
	SharingSession session = convert(dto, SharingSession.class);
	session.setUser(user);
	session.setSubject(subject);
	subjectRepository.setSessionScheduleTrue(dto.getSubjectId());
	SharingSession sessionToSave = sharingSessionRepository.save(session);
	return convert(sessionToSave, SharingSessionViewDto.class);
    }

    @Override
    public List<SharingSessionViewDto> getAllSessions() {
	List<SharingSessionViewDto> list = convertList(
	        sharingSessionRepository.getAllSessionWithUserEnable(),
	        SharingSessionViewDto.class);
	return list;
    }

    @Override
    public boolean isDateValid(LocalDateTime dateTime) {
	return !sharingSessionRepository.existsByStartTimeBetween(
	        setToTime(dateTime, 00, 00, 00),
	        setToTime(dateTime, 23, 59, 59));
    }

    /**
     * Convert a {@code LocalDateTime} to set a specific time.
     *
     * @param dateTime the date time to modify
     * @param hours    the choosen hours
     * @param minutes  the choosen minutes
     * @param seconds  the choosen seconds
     * @return the date time formatteds
     *
     * @author Tomas LOBGEOIS
     */
    private LocalDateTime setToTime(LocalDateTime dateTime, int hours,
            int minutes, int seconds) {
	return dateTime.withHour(hours).withMinute(minutes).withSecond(seconds);
    }
}
