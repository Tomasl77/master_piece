package fr.formation.masterpiece.commons.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

import fr.formation.masterpiece.api.repositories.CustomUserRepository;
import fr.formation.masterpiece.api.services.EmailService;
import fr.formation.masterpiece.domain.dtos.views.SharingSessionViewDto;
import fr.formation.masterpiece.domain.entities.CustomUser;
import fr.formation.masterpiece.domain.entities.Mail;

@Component
public class EmailManager {

    private final EmailService emailService;

    private final CustomUserRepository userRepository;

    public EmailManager(EmailService emailService,
            CustomUserRepository userRepository) {
	this.emailService = emailService;
	this.userRepository = userRepository;
    }

    private List<String> getRecipients() {
	List<CustomUser> users = userRepository.findAll();
	List<String> recipients = new ArrayList<>();
	users.forEach(user -> recipients.add(user.getEmail()));
	return recipients;
    }

    public void buildSessionMail(SharingSessionViewDto session)
            throws MessagingException {
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
	Mail mail = new Mail("SyK", getRecipients(),
	        "New session : " + session.getSubject().getTitle(), content);
	send(mail);
    }

    private void send(Mail mail) throws MessagingException {
	emailService.sendMail(mail);
    }

    private String formatDate(LocalDateTime dateTime) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	return dateTime.format(formatter);
    }

    private String formatTime(LocalDateTime dateTime) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");
	return dateTime.format(formatter);
    }
}
