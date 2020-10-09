package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.config.JUnitConfigTest;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.security.annotations.MockAdminForTests;

class UserControllerTest extends JUnitConfigTest {

    @Autowired
    private UserController userController;

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
}
