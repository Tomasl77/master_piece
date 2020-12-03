package fr.formation.masterpiece.domain.entities;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class CustomUserTest extends JUnitConfigTest {

    @Test
    void should_construct_username_password_roles() {
	Role role = new Role("ROLE_USER");
	Set<Role> roles = new HashSet<>();
	roles.add(role);
	CustomUser tested = new CustomUser("password", "Johanna",
	        roles);
	assertEquals("password", tested.getPassword());
	assertEquals("Johanna", tested.getUsername());
	assertEquals(1, tested.getRoles().size());
    }
}
