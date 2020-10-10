package fr.formation.masterpiece.domain.dtos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class CustomUserCreateDtoTest extends JUnitConfigTest {

    @Test
    void should_set_username() {
	CustomUserCreateDto tested = new CustomUserCreateDto();
	tested.setUsername("Tomas");
	assertEquals("Tomas", tested.getUsername());
    }

    @Test
    void should_set_password() {
	CustomUserCreateDto tested = new CustomUserCreateDto();
	tested.setPassword("Administrator9!");
	assertEquals("Administrator9!", tested.getPassword());
    }

    @Test
    void should_construct() {
	CustomUserCreateDto actual = new CustomUserCreateDto();
	assertNotNull(actual);
    }

    @Test
    void should_have_message() {
	CustomUserCreateDto tested = new CustomUserCreateDto();
	assertNotNull(tested.getMessage());
    }

    @Test
    void should_have_pattern() {
	CustomUserCreateDto tested = new CustomUserCreateDto();
	assertNotNull(tested.getPattern());
    }
}
