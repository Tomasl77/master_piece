package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.api.controllers.UserController;
import fr.formation.masterpiece.config.JUnitConfigTest;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;
import fr.formation.masterpiece.security.annotations.MockAdminForTests;
import fr.formation.masterpiece.security.annotations.MockUserForTests;

class UserControllerTest extends JUnitConfigTest {

    private final String jsonCreateUser = "{   \"email\": \"thierry@gmail.com\",\n"
            + "         \"username\" : \"Thierry\",\n"
            + "        \"password\" : \"Benjahmsin9!\"\n }";

    @Autowired
    private UserController userController;

    @Test
    void should_return_username_not_valid() {
	UsernameCheckDto tested = userController.checkUsername("Tomas");
	assertFalse(tested.isValid());
    }

    @Test
    @MockUserForTests
    void should_get_by_id() {
	CustomUserViewDto tested = userController.getOne(1L);
	assertEquals("Tomas", tested.getUsername());
	assertEquals("lobgeois.tomas@free.fr", tested.getEmail());
    }

    @Test
    void should_return_email_valid() {
	UserEmailCheckDto tested = userController.checkEmail("aeris@gmail.com");
	assertTrue(tested.isValid());
    }

    @Test
    @MockAdminForTests
    void should_create_user() {
	int actual = userController.getAll().size();
	CustomUserCreateDto dto = jsonConvert(jsonCreateUser,
	        CustomUserCreateDto.class);
	CustomUserDto tested = userController.create(dto);
	int expected = userController.getAll().size();
	assertEquals("Thierry", tested.getUsername());
	assertEquals("thierry@gmail.com", tested.getEmail());
	assertEquals(expected, actual + 1);
    }
}
