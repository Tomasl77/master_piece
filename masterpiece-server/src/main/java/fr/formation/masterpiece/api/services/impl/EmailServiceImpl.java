package fr.formation.masterpiece.api.services.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.api.services.EmailService;
import fr.formation.masterpiece.domain.entities.Mail;

/**
 * Default concrete implementation of {@link EmailService}
 *
 * @author Tomas LOBGEOIS
 *
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final String ENCODING = "utf-8";

    private static final String TYPE = "text/html";

    private final JavaMailSender javaMailSender;

    /**
     * Protected constructor to autowire needed beans.
     * <p>
     * injects {@code JavaMailSender}
     *
     * @param javaMailSender the injected {@code JavaMailSender} bean.
     */
    protected EmailServiceImpl(JavaMailSender javaMailSender) {
	this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(Mail mail) throws MessagingException {
	MimeMessage message = javaMailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(message, true,
	        ENCODING);
	helper.setFrom(mail.getSender());
	helper.setTo(mail.getRecipients().toArray(new String[0]));
	helper.setSubject(mail.getSubject());
	message.setContent(mail.getContent(), TYPE);
	javaMailSender.send(message);
    }
}
