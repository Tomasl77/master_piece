package fr.formation.masterpiece.domain.entities;

import java.util.List;

/**
 * Representation of a mail.
 * <p>
 * Contains all needed informations for {@code JavaMailSender} to send a mail.
 *
 * @author Tomas LOBGEOIS
 */
public class Mail {

    private String sender;

    private List<String> recipients;

    private String subject;

    private String content;

    public Mail(String sender, List<String> recipients, String subject,
            String content) {
	this.sender = sender;
	this.recipients = recipients;
	this.subject = subject;
	this.content = content;
    }

    public String getSender() {
	return sender;
    }

    public List<String> getRecipients() {
	return recipients;
    }

    public String getSubject() {
	return subject;
    }

    public String getContent() {
	return content;
    }
}
