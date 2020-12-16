package fr.formation.masterpiece.domain.dtos.users;

import static org.junit.Assert.assertNull;
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
    void should_have_email() {
	CustomUserCreateDto tested = new CustomUserCreateDto();
	assertNull(tested.getEmail());
    }
}
