package fr.formation.masterpiece.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class MailTest extends UnitTestConfig {

    private static Mail actual;

    @BeforeAll
    static void before() {
	String sender = "SyK";
	List<String> recipients = List.of("mokrane@gmail.com",
	        "rachid@gmail.com");
	String subject = "Test case";
	String content = "I want to unit test this method";
	actual = new Mail(sender, recipients, subject, content);
    }

    @Test
    void should_construct_mail() {
	assertNotNull(actual);
    }

    @Test
    void should_get_sender() {
	assertEquals("SyK", actual.getSender());
    }

    @Test
    void should_get_subject() {
	assertEquals("Test case", actual.getSubject());
    }

    @Test
    void should_get_content() {
	assertEquals("I want to unit test this method", actual.getContent());
    }

    @Test
    void should_get_list_of_recipients() {
	assertEquals(2, actual.getRecipients().size());
    }
}
