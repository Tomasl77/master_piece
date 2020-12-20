package fr.formation.masterpiece.security;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import fr.formation.masterpiece.config.UnitTestConfig;

class CustomUserDetailsTest extends UnitTestConfig {

    private static User user;

    private static CustomUserDetails details;

    @BeforeAll
    static void set_up() {
	Set<GrantedAuthority> authorities = new HashSet<>();
	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	user = new User("Tomas", "Tomasuser9!", true, true, true, true,
	        authorities);
	details = new CustomUserDetails(user, 1L);
    }

    @Test
    void should_get_id() {
	Long expected = 1L;
	assertEquals(expected, details.getId());
    }

    @Test
    void should_return_to_string() {
	String expected = "{id: 1, authorities: [ROLE_USER], "
	        + "password: [PROTECTED], username: Tomas, "
	        + "enabled: true, accountNonExpired: true, "
	        + "accountNonLocked: true, credentialsNonExpired: true}";
	assertEquals(expected, details.toString());
    }
}
