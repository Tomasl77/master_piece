package fr.formation.masterpiece.api.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.masterpiece.api.repositories.EntityUserRepository;
import fr.formation.masterpiece.api.repositories.SharingSessionRepository;
import fr.formation.masterpiece.api.repositories.SubjectRepository;
import fr.formation.masterpiece.api.services.SharingSessionService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.utils.EmailManager;
import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionDto;
import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionViewDto;
import fr.formation.masterpiece.domain.entities.EntityUser;
import fr.formation.masterpiece.domain.entities.Mail;
import fr.formation.masterpiece.domain.entities.SharingSession;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.security.SecurityHelper;
import javaslang.Tuple2;

/**
 * Default concrete implementation of {@link SharingSessionService}
 *
 * @author Tomas LOBGEOIS
 *
 */
@Service
public class SharingSessionServiceImpl extends AbstractService
        implements SharingSessionService {

    private final EntityUserRepository userRepository;

    private final SubjectRepository subjectRepository;

    private final SharingSessionRepository sharingSessionRepository;

    private final EmailManager emailManager;

    public SharingSessionServiceImpl(EntityUserRepository userRepository,
            SubjectRepository subjectRepository,
            SharingSessionRepository sharingSessionRepository,
            EmailManager emailManager) {
	this.userRepository = userRepository;
	this.subjectRepository = subjectRepository;
	this.sharingSessionRepository = sharingSessionRepository;
	this.emailManager = emailManager;
    }

    @Override
    @Transactional
    public void create(SharingSessionCreateDto dto) throws MessagingException {
	Long userId = SecurityHelper.getUserId();
	EntityUser user = userRepository.getOne(userId);
	Subject subject = subjectRepository.getOne(dto.getSubjectId());
	SharingSession session = convert(dto, SharingSession.class);
	session.setUser(user);
	session.setSubject(subject);
	subjectRepository.setSessionScheduleTrue(dto.getSubjectId());
	SharingSession sessionToSave = sharingSessionRepository.save(session);
	SharingSessionDto savedDto = convert(sessionToSave,
	        SharingSessionDto.class);
	Tuple2<Map<String, Object>, String> mailToConstruct = buildArgsAndGetTemplate(
	        savedDto);
	String content = emailManager.buildMailContent(mailToConstruct._1,
	        mailToConstruct._2);
	Mail mail = emailManager.buildTeamMail(savedDto.getSubjectTitle(), content);
	emailManager.send(mail);
    }

    @Override
    public List<SharingSessionViewDto> getAllSessions() {
	LocalDateTime now = getNow();
	return sharingSessionRepository.getAllSessionWithUserEnable(now);
    }

    /**
     * Private method to get now as {@code LocalDateTime}
     *
     * @return A date time format of now
     */
    private LocalDateTime getNow() {
	return LocalDateTime.now();
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
     * @return the date time formatted
     *
     * @author Tomas LOBGEOIS
     */
    private LocalDateTime setToTime(LocalDateTime dateTime, int hours,
            int minutes, int seconds) {
	return dateTime.withHour(hours).withMinute(minutes).withSecond(seconds);
    }

    /**
     * Format a {@code LocalDateTime} into a format {@code LocalDate}
     *
     * @param dateTime the date time to format
     * @return the date time into format date
     */
    private String formatDate(LocalDateTime dateTime) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	return dateTime.format(formatter);
    }

    /**
     * Format a {@code LocalDateTime} into a format {@code LocalTime}
     *
     * @param dateTime the date time to format
     * @return the date time into format time
     */
    private String formatTime(LocalDateTime dateTime) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	return dateTime.format(formatter);
    }

    private Tuple2<Map<String, Object>, String> buildArgsAndGetTemplate(
            SharingSessionDto savedDto) {
	String template = "NewSessionMail";
	Map<String, Object> args = new HashMap<>();
	args.put("lecturer", savedDto.getLecturer());
	args.put("date", formatDate(savedDto.getStartTime()));
	args.put("start", formatTime(savedDto.getStartTime()));
	args.put("end", formatTime(savedDto.getEndTime()));
	return new Tuple2<>(args, template);
    }
}
