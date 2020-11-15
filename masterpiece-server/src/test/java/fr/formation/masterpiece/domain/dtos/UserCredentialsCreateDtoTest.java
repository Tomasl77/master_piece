package fr.formation.masterpiece.domain.dtos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserCredentialsCreateDtoTest extends JUnitConfigTest {

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
