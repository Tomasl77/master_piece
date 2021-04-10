package fr.formation.masterpiece.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import fr.formation.masterpiece.api.repositories.EntityUserRepository;
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

    private final EntityUserRepository userRepository;

    private final TemplateEngine templateEngine;

    public EmailManager(EmailService emailService,
            EntityUserRepository userRepository,
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
	args.put("signature",
	        "Don't miss an opporunity to raise your knowledge");
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
