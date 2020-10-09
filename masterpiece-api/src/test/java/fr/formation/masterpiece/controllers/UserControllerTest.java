package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.config.JUnitConfigTest;
import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.repositories.UserJpaRepository;
import fr.formation.masterpiece.security.annotations.MockAdminForTests;

class UserControllerTest extends JUnitConfigTest {

    private final String jsonCreateUser = "{ \n"
            + "    \"username\" : \"Thierry\",\n"
            + "    \"password\" : \"Thierry9!\",\n" + "    \"userInfo\": {\n"
            + "        \"email\" : \"thierry@gmail.com\"\n" + "    }  \n" + "}";

    @Autowired
    private UserController userController;

    @Autowired
    private UserJpaRepository userRepository;

    @Test
    @MockAdminForTests
    void should_return_all_member() {
	List<CustomUserViewDto> actual = userController.getAll();
	assertEquals(4, actual.size());
    }

    @Test
    void should_return_username_not_valid() {
	UsernameCheckDto tested = userController.checkUsername("Tomas");
	assertFalse(tested.isValid());
    }

    @Test
    void should_get_by_id() {
	CustomUserViewDto tested = userController.getOne(1L);
	assertEquals("Tomas", tested.getUsername());
	assertEquals("tomas@gmail.com", tested.getInfo().getEmail());
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
	assertEquals("thierry@gmail.com", tested.getInfo().getEmail());
	assertEquals(expected, actual + 1);
    }
}
