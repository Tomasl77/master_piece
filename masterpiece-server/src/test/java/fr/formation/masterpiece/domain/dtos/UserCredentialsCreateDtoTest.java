package fr.formation.masterpiece.domain.dtos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserCredentialsCreateDtoTest extends JUnitConfigTest {

    @Test
    void should_set_username() {
	UserCredentialsCreateDto tested = new UserCredentialsCreateDto();
	tested.setUsername("Tomas");
	assertEquals("Tomas", tested.getUsername());
    }

    @Test
    void should_set_password() {
	UserCredentialsCreateDto tested = new UserCredentialsCreateDto();
	tested.setPassword("Administrator9!");
	assertEquals("Administrator9!", tested.getPassword());
    }

    @Test
    void should_construct() {
	UserCredentialsCreateDto actual = new UserCredentialsCreateDto();
	assertNotNull(actual);
    }

    @Test
    void should_have_message() {
	UserCredentialsCreateDto tested = new UserCredentialsCreateDto();
	assertNotNull(tested.getMessage());
    }

    @Test
    void should_have_pattern() {
	UserCredentialsCreateDto tested = new UserCredentialsCreateDto();
	assertNotNull(tested.getPattern());
    }
}
