package fr.formation.masterpiece.api.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserInfoDetailsServiceImplTest extends JUnitConfigTest {

    @Autowired
    private UserInfoDetailsServiceImpl service;

    @Test
    void should_throw_username_not_found() {
	assertThrows(UsernameNotFoundException.class,
	        () -> service.loadUserByUsername("Aeris"));
    }

    @Test
    void should_find_user() {
	UserDetails expected = service.loadUserByUsername("Tomas");
	assertNotNull(expected);
    }
}
