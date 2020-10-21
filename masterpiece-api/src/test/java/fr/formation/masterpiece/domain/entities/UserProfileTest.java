package fr.formation.masterpiece.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserProfileTest extends JUnitConfigTest {

    private static String userTested = "{ \"email\": \"userTested@gmail.com\","
            + " \"credentials\": {" + " \"username\" : \"userTested\","
            + " \"password\" : \"testedUser9!\"" + " } " + "}";

    @Test
    void void_should_set_email() {
	UserProfile tested = new UserProfile();
	tested.setEmail("aeris@gmail.com");
	assertEquals("aeris@gmail.com", tested.getEmail());
    }

    void should_set_user_credentials() {
	UserProfile tested = new UserProfile();
	UserCredentials credentials = jsonConvert(
	        "{" + " \"username\" : \"userTested\","
	                + " \"password\" : \"testedUser9!\"" + " }",
	        UserCredentials.class);
	tested.setCredentials(credentials);
	assertEquals("userTested", tested.getCredentials().getUsername());
	assertEquals("testedUser9!", tested.getCredentials().getPassword());
    }

    @Test
    void should_return_to_string() {
	UserProfile tested = jsonConvert(userTested, UserProfile.class);
	String expected = "{email: userTested@gmail.com, "
	        + "credentials: {id: null, username: userTested, password: [PROTECTED], roles: null, enabled: false}}";
	assertEquals(expected, tested.toString());
    }
}
