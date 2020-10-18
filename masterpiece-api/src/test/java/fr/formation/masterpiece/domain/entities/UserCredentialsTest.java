package fr.formation.masterpiece.domain.entities;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserCredentialsTest extends JUnitConfigTest {

    @Test
    void should_construct_username_password_roles() {
	Set<Role> roles = new HashSet<>();
	UserCredentials credentials = new UserCredentials("password",
	        "username", roles);
    }
}
