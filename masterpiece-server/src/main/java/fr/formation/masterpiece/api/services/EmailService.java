package fr.formation.masterpiece.api.services;

import javax.mail.MessagingException;

import fr.formation.masterpiece.domain.entities.Mail;

/**
 * Service to handle {@code Mail} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface EmailService {

    /**
     * Send given {@code Mail} with {@code JavaMailSender}
     *
     * @param mail the given {@code Mail} to be sent
     * @throws MessagingException occurs if error is encountered by
     *                            {@code JavaMailService}
     */
    void sendMail(Mail mail) throws MessagingException;
}
