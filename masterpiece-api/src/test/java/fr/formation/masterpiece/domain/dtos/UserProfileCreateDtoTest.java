package fr.formation.masterpiece.domain.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserProfileCreateDtoTest extends JUnitConfigTest {

    @Test
    void should_return_email() {
	UserProfileCreateDto tested = new UserProfileCreateDto();
	tested.setEmail("johanna@gmail.com");
	assertEquals("johanna@gmail.com", tested.getEmail());
    }

    @Test
    void should_return_to_string() {
	UserProfileCreateDto tested = new UserProfileCreateDto();
	tested.setEmail("johanna@gmail.com");
	String expected = "{email: johanna@gmail.com , credentials: null}";
	assertEquals(expected, tested.toString());
    }
}
