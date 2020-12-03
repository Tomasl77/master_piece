package fr.formation.masterpiece.api.services;

import javax.mail.MessagingException;

import fr.formation.masterpiece.domain.entities.Mail;

/**
 * Service to handle {@code Mail} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface EmailService {

    void sendMail(Mail mail) throws MessagingException;
}
