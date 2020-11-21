package fr.formation.masterpiece.api.services;

import javax.mail.MessagingException;

import fr.formation.masterpiece.domain.entities.Mail;

public interface EmailService {

    void sendMail(Mail mail) throws MessagingException;
}
