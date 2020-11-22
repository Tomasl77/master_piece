package fr.formation.masterpiece.api.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.api.repositories.SharingSessionRepository;
import fr.formation.masterpiece.api.repositories.SubjectRepository;
import fr.formation.masterpiece.api.repositories.UserProfileRepository;
import fr.formation.masterpiece.api.services.EmailService;
import fr.formation.masterpiece.api.services.SharingSessionService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.views.SharingSessionViewDto;
import fr.formation.masterpiece.domain.entities.Mail;
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

    private final EmailService emailService;

    public SharingSessionServiceImpl(
            UserProfileRepository userProfileRepository,
            SubjectRepository subjectRepository,
            SharingSessionRepository sharingSessionRepository,
            EmailService emailService) {
	this.userProfileRepository = userProfileRepository;
	this.subjectRepository = subjectRepository;
	this.sharingSessionRepository = sharingSessionRepository;
	this.emailService = emailService;
    }

    @Override
    @Transactional
    public void create(SharingSessionCreateDto dto) throws MessagingException {
	Long userId = SecurityHelper.getUserId();
	UserProfile userProfile = userProfileRepository
	        .findProfileWithUserCredentialsId(userId)
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "User id: " + userId + " doesn't exist"));
	Subject subject = subjectRepository.getOne(dto.getSubjectId());
	SharingSession session = convert(dto, SharingSession.class);
	session.setUserProfile(userProfile);
	session.setSubject(subject);
	subjectRepository.setSessionScheduleTrue(dto.getSubjectId());
	sharingSessionRepository.save(session);
	Mail mail = buildSessionMail(session);
	emailService.sendMail(mail);
    }

    private Mail buildSessionMail(SharingSession session) {
	List<String> recipients = getRecipients();
	StringBuilder builder = new StringBuilder();
	String content = builder.append("New session booked")
	        .append(System.lineSeparator())
	        .append("Date : " + formatDate(session.getStartTime()))
	        .append(System.lineSeparator())
	        .append("Starting time : " + formatTime(session.getStartTime()))
	        .append(System.lineSeparator())
	        .append("Ending time : " + formatTime(session.getEndTime()))
	        .append(System.lineSeparator())
	        .append(" The lecturer will be : " + session.getUserProfile()
	                .getCredentials().getUsername())
	        .toString();
	Mail mail = new Mail("SyK", recipients,
	        "New session : " + session.getSubject().getTitle(), content);
	return mail;
    }

    private List<String> getRecipients() {
	List<UserProfile> users = userProfileRepository.findAll();
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

    @Override
    public List<SharingSessionViewDto> getAllSessions() {
	List<SharingSessionViewDto> list = convertList(
	        sharingSessionRepository.findAll(),
	        SharingSessionViewDto.class);
	return list;
    }
}
