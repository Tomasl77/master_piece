package fr.formation.masterpiece.api.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.config.UnitTestConfig;

class UserServiceImplTest extends UnitTestConfig {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void should_return_valid_email() {
	assertTrue(userService.isEmailValid("aeris@gmail.com"));
    }

    @Test
    public void should_return_unvalid_email() {
	assertFalse(userService.isEmailValid("lily@gmail.com"));
    }

    @Test
    public void should_return_username_valid() {
	assertTrue(userService.isUsernameValid("Aeris"));
    }

    @Test
    public void should_return_unvalid_username() {
	assertFalse(userService.isUsernameValid("Lily"));
    }

    @Test
    public void should_return_email_not_valid_to_string() {
	String expected = "{isValid: false}";
	String actual = "lily@gmail.com";
	assertEquals(expected, userService.checkEmail(actual).toString());
    }

    @Test
    public void should_find_user_by_id() {
	assertNotNull(userService.getOne(1L));
    }

    @Test
    public void should_throw_resource_not_found_exception() {
	assertThrows(ResourceNotFoundException.class,
	        () -> userService.getOne(99L));
    }

    @Test
    public void should_return_username_not_valid_to_string() {
	String expected = "{isValid: false}";
	String actual = "Lily";
	assertEquals(expected, userService.checkUsername(actual).toString());
    }
}
