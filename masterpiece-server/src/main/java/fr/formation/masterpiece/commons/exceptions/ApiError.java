package fr.formation.masterpiece.commons.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

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

    private HttpStatus status;

    private String message;

    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
	super();
	this.status = status;
	this.message = message;
	this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
	super();
	this.status = status;
	this.message = message;
	this.errors = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
	return status;
    }
}