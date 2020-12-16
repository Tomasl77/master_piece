package fr.formation.masterpiece.commons.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import fr.formation.masterpiece.config.JUnitConfigTest;

class ApiErrorTest extends JUnitConfigTest {

    @Test
    void should_construct_single_error() {
	ApiError tested = new ApiError(HttpStatus.BAD_REQUEST, "error_message",
	        "bad_request");
	assertEquals(HttpStatus.BAD_REQUEST, tested.getStatus());
	assertEquals("error_message", tested.getMessage());
    }

    @Test
    void should_construct_multiple_error() {
	List<String> errors = new ArrayList<>();
	errors.add("uniqueEmailValidation");
	errors.add("uniqueUsernameValidation");
	ApiError tested = new ApiError(HttpStatus.BAD_REQUEST, "error_message",
	        errors);
	assertEquals(HttpStatus.BAD_REQUEST, tested.getStatus());
	assertEquals("error_message", tested.getMessage());
	assertEquals("[uniqueEmailValidation, uniqueUsernameValidation]",
	        tested.getErrors());
    }
}
