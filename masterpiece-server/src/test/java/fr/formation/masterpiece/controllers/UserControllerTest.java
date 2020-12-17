package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.api.controllers.UserController;
import fr.formation.masterpiece.config.JUnitConfigTest;
import fr.formation.masterpiece.domain.dtos.users.CustomUserCreateDto;
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
    @MockUserForTests
    void should_get_by_id() {
	CustomUserViewDto tested = userController.getOne(1L);
	assertEquals("Tomas", tested.getUsername());
    }

    @Test
    @MockAdminForTests
    void should_create_user() {
	int actual = userController.getAll().size();
	CustomUserCreateDto dto = jsonConvert(jsonCreateUser,
	        CustomUserCreateDto.class);
	userController.create(dto);
	int expected = userController.getAll().size();
	assertEquals(expected, actual + 1);
    }
}
