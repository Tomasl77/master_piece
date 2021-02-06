package fr.formation.masterpiece.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import fr.formation.masterpiece.api.repositories.CustomUserRepository;
import fr.formation.masterpiece.api.services.EmailService;
import fr.formation.masterpiece.domain.entities.EntityUser;
import fr.formation.masterpiece.domain.entities.Mail;

/**
 * Component to manage email building and sending through application
 *
 * @author Tomas LOBGEOIS
 *
 */
@Component
public class EmailManager {

    private final EmailService emailService;

    private final CustomUserRepository userRepository;

    private final TemplateEngine templateEngine;

    public EmailManager(EmailService emailService,
            CustomUserRepository userRepository,
            TemplateEngine templateEngine) {
	this.emailService = emailService;
	this.userRepository = userRepository;
	this.templateEngine = templateEngine;
    }

    private List<String> getRecipients() {
	List<EntityUser> users = userRepository.findAll();
	List<String> recipients = new ArrayList<>();
	users.forEach(user -> recipients.add(user.getEmail()));
	return recipients;
    }

    public String buildMailContent(Map<String, Object> args, String template) {
	Context context = new Context();
	context.setVariables(args);
	return templateEngine.process(template, context);
    }

    public Mail buildMail(String title, String content) {
	return new Mail("SyK", getRecipients(), title, content);
    }

    public void send(Mail mail) throws MessagingException {
	emailService.sendMail(mail);
    }
}
