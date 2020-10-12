package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.config.JUnitConfigTest;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UserProfileCreateDto;
import fr.formation.masterpiece.domain.dtos.UserProfileDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.UserProfileViewDto;
import fr.formation.masterpiece.security.annotations.MockAdminForTests;

class UserControllerTest extends JUnitConfigTest {

    private final String jsonCreateUser = "{   \"email\": \"thierry@gmail.com\",\n"
            + "    \"credentials\": {\n"
            + "         \"username\" : \"Thierry\",\n"
            + "        \"password\" : \"Benjahmsin9!\"\n" + "    }  \n" + "}";

    @Autowired
    private UserController userController;

    @Test
    @MockAdminForTests
    void should_return_all_member() {
	List<UserProfileViewDto> actual = userController.getAll();
	assertEquals(4, actual.size());
    }

    @Test
    void should_return_username_not_valid() {
	UsernameCheckDto tested = userController.checkUsername("Tomas");
	assertFalse(tested.isValid());
    }

    @Test
    void should_get_by_id() {
	UserProfileViewDto tested = userController.getOne(1L);
	assertEquals("Tomas", tested.getCredentials().getUsername());
	assertEquals("tomas@gmail.com", tested.getEmail());
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
	UserProfileCreateDto dto = jsonConvert(jsonCreateUser,
	        UserProfileCreateDto.class);
	UserProfileDto tested = userController.create(dto);
	int expected = userController.getAll().size();
	assertEquals("Thierry", tested.getCredentials().getUsername());
	assertEquals("thierry@gmail.com", tested.getEmail());
	assertEquals(expected, actual + 1);
    }
}
