package fr.formation.masterpiece.commons.exceptions;

import java.util.Arrays;
import java.util.List;

/**
 * Object encapsulating error informations.
 * <p>
 * <ul>
 * <li>status : the status code of the error</li>
 * <li>message : message given by default by the exception</li>
 * <li>{@code List} errors : errors thrown by default by the exception</li>
 * </ul>
 *
 * @author Tomas LOBGEOIS
 */
class ApiError {

    private String message;

    private List<String> errors;

    public ApiError(String message, List<String> errors) {
	this.message = message;
	this.errors = errors;
    }

    public ApiError(String message, String error) {
	this.message = message;
	this.errors = Arrays.asList(error);
    }
}