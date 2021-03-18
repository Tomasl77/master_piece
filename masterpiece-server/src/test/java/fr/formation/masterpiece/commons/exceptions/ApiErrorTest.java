package fr.formation.masterpiece.commons.exceptions;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class ApiErrorTest extends UnitTestConfig {

    @Test
    void should_construct_multiple_error() {
	List<String> errors = new ArrayList<>();
	errors.add("uniqueEmailValidation");
	errors.add("uniqueUsernameValidation");
	ApiError tested = new ApiError("error_message", errors);
	assertNotNull(tested);
    }
}
