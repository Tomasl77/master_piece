package fr.formation.masterpiece.domain.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserInfoCreateDtoTest extends JUnitConfigTest {

    @Test
    void should_return_email() {
	UserInfoCreateDto tested = new UserInfoCreateDto();
	tested.setEmail("johanna@gmail.com");
	assertEquals("johanna@gmail.com", tested.getEmail());
    }

    @Test
    void should_return_to_string() {
	UserInfoCreateDto tested = new UserInfoCreateDto();
	tested.setEmail("johanna@gmail.com");
	String expected = "{email: johanna@gmail.com}";
	assertEquals(expected, tested.toString());
    }
}
