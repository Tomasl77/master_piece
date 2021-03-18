package fr.formation.masterpiece.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import fr.formation.masterpiece.config.UnitTestConfig;

class EntityUserTest extends UnitTestConfig {

    @Test
    void should_construct_empty_constructor() {
	EntityUser tested = new EntityUser();
	assertNotNull(tested);
    }

    @Test
    void should_construct_complete_constructor() {
	Role role = new Role("ROLE_USER");
	Set<Role> roles = new HashSet<>();
	roles.add(role);
	EntityUser tested = new EntityUser("password", "Thierry", roles, true,
	        true, true, true, "thierry@gmail.com");
	assertEquals("password", tested.getPassword());
	assertEquals("Thierry", tested.getUsername());
	assertEquals(1, tested.getRoles().size());
	assertTrue(tested.isEnabled());
	assertTrue(tested.isAccountNonExpired());
	assertTrue(tested.isAccountNonLocked());
	assertTrue(tested.isCredentialsNonExpired());
	assertEquals("thierry@gmail.com", tested.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "{id: null, username: Thierry, password: [PROTECTED], enabled: true, email: thierry@gmail.com}" })
    void should_return_to_string(String toString) {
	Role role = new Role("ROLE_USER");
	Set<Role> roles = new HashSet<>();
	roles.add(role);
	EntityUser tested = new EntityUser("password", "Thierry", roles, true,
	        true, true, true, "thierry@gmail.com");
	assertEquals(toString, tested.toString());
    }
}
