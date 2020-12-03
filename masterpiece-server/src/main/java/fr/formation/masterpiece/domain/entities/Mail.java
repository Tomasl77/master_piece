package fr.formation.masterpiece.domain.entities;

import java.util.List;

import lombok.Getter;

@Getter
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
}