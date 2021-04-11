package fr.formation.masterpiece.commons.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.config.UnitTestConfig;
import fr.formation.masterpiece.domain.entities.Mail;

class EmailManagerTest extends UnitTestConfig {

    @Autowired
    private EmailManager manager;

    private static final List<String> CUSTOM_RECIPIENTS = Arrays
            .asList("tomas@gmail.com", "thierry@gmail.com");

    @Test
    void should_get_content_team_mail() {
	Mail testedTeamMail = constructTeamMail();
	assertEquals(testedTeamMail.getContent(), "TestContentTeam");
    }

    @Test
    void should_get_title_team_mail() {
	Mail testedTeamMail = constructTeamMail();
	assertEquals(testedTeamMail.getSubject(), "TestSubjectTeam");
    }

    @Test
    void should_size_recipients_team_mail() {
	Mail testedTeamMail = constructTeamMail();
	assertEquals(testedTeamMail.getRecipients().size(), 5);
    }

    @Test
    void should_get_sender_team_mail() {
	Mail testedTeamMail = constructTeamMail();
	assertEquals(testedTeamMail.getSender(), "SyK");
    }

    @Test
    void should_get_content_custom_mail() {
	Mail testedCustomMail = constructCustomMail();
	assertEquals(testedCustomMail.getContent(), "TestContentCustom");
    }

    @Test
    void should_get_title_custom_mail() {
	Mail testedCustomMail = constructCustomMail();
	assertEquals(testedCustomMail.getSubject(), "TestSubjectCustom");
    }

    @Test
    void should_size_recipients_custom_mail() {
	Mail testedCustomMail = constructCustomMail();
	assertEquals(testedCustomMail.getRecipients().size(), 2);
    }

    @Test
    void should_get_sender_custom_mail() {
	Mail testedCustomMail = constructCustomMail();
	assertEquals(testedCustomMail.getSender(), "SyK");
    }

    private Mail constructTeamMail() {
	return manager.buildTeamMail("TestSubjectTeam", "TestContentTeam");
    }

    private Mail constructCustomMail() {
	return manager.buildMail("TestSubjectCustom", "TestContentCustom",
	        CUSTOM_RECIPIENTS);
    }
}
