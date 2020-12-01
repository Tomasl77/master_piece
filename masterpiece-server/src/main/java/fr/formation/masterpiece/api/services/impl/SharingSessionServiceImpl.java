package fr.formation.masterpiece.api.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.api.repositories.CustomUserRepository;
import fr.formation.masterpiece.api.repositories.SharingSessionRepository;
import fr.formation.masterpiece.api.repositories.SubjectRepository;
import fr.formation.masterpiece.api.services.EmailService;
import fr.formation.masterpiece.api.services.SharingSessionService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.views.SharingSessionViewDto;
import fr.formation.masterpiece.domain.entities.CustomUser;
import fr.formation.masterpiece.domain.entities.Mail;
import fr.formation.masterpiece.domain.entities.SharingSession;
import fr.formation.masterpiece.domain.entities.Subject;
import fr.formation.masterpiece.security.SecurityHelper;

@Service
public class SharingSessionServiceImpl extends AbstractService
        implements SharingSessionService {

    private final CustomUserRepository userRepository;

    private final SubjectRepository subjectRepository;

    private final SharingSessionRepository sharingSessionRepository;

    private final EmailService emailService;

    public SharingSessionServiceImpl(CustomUserRepository userRepository,
            SubjectRepository subjectRepository,
            SharingSessionRepository sharingSessionRepository,
            EmailService emailService) {
	this.userRepository = userRepository;
	this.subjectRepository = subjectRepository;
	this.sharingSessionRepository = sharingSessionRepository;
	this.emailService = emailService;
    }

    @Override
    @Transactional
    public SharingSessionViewDto create(SharingSessionCreateDto dto)
            throws MessagingException {
	Long userId = SecurityHelper.getUserId();
	CustomUser userProfile = userRepository.findById(userId)
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "User id: " + userId + " doesn't exist"));
	Subject subject = subjectRepository.getOne(dto.getSubjectId());
	SharingSession session = convert(dto, SharingSession.class);
	session.setUser(userProfile);
	session.setSubject(subject);
	subjectRepository.setSessionScheduleTrue(dto.getSubjectId());
	SharingSession sessionToSave = sharingSessionRepository.save(session);
	return convert(sessionToSave, SharingSessionViewDto.class);
    }

    @Override
    public void buildSessionMail(SharingSessionViewDto session)
            throws MessagingException {
	List<String> recipients = getRecipients();
	StringBuilder builder = new StringBuilder();
	String content = builder.append("New session booked").append("<p>")
	        .append("Date : " + formatDate(session.getStartTime()))
	        .append("<p>")
	        .append("Starting time : " + formatTime(session.getStartTime()))
	        .append("<p>")
	        .append("Ending time : " + formatTime(session.getEndTime()))
	        .append("<p>").append(" The lecturer will be : "
	                + session.getUser().getUsername())
	        .toString();
	Mail mail = new Mail("SyK", recipients,
	        "New session : " + session.getSubject().getTitle(), content);
	emailService.sendMail(mail);
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

    private List<String> getRecipients() {
	List<CustomUser> users = userRepository.findAll();
	List<String> recipients = new ArrayList<>();
	users.forEach(user -> recipients.add(user.getEmail()));
	return recipients;
    }

    private String formatDate(LocalDateTime dateTime) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	return dateTime.format(formatter);
    }

    private String formatTime(LocalDateTime dateTime) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");
	return dateTime.format(formatter);
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
