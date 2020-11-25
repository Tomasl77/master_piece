package fr.formation.masterpiece.domain.dtos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class CustomUserCreateDtoTest extends JUnitConfigTest {

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
