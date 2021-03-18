package fr.formation.masterpiece.commons.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom controller advice to handle all {@link RestController} exceptions.
 * <p>
 * Manages handlers for exceptions to mutualize and standardize exception
 * handling for all {@link RestController}
 *
 * @author Tomas LOBGEOIS
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler
        extends ResponseEntityExceptionHandler {

    private final static Log LOGGER = LogFactory
            .getLog(GlobalControllerExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
	List<String> errors = new ArrayList<>();
	for (FieldError error : ex.getFieldErrors()) {
	    errors.add(error.getCode() + ": " + error.getField() + " ("
	            + error.getDefaultMessage() + ")");
	}
	for (ObjectError error : ex.getGlobalErrors()) {
	    errors.add(error.getObjectName() + ": " + error.getCode());
	}
	ApiError apiError = new ApiError("Validation failed for argument(s): ",
	        errors);
	LOGGER.trace(apiError, ex);
	return super.handleExceptionInternal(ex, apiError, headers,
	        HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
	StringBuilder builder = new StringBuilder();
	builder.append(ex.getMethod());
	builder.append(
	        " method is not supported for this request. Supported methods are ");
	ex.getSupportedHttpMethods()
	        .forEach(method -> builder.append(method + " "));
	ApiError apiError = new ApiError(ex.getMessage(), builder.toString());
	return handleExceptionInternal(ex, apiError, null,
	        HttpStatus.METHOD_NOT_ALLOWED, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
	String error = "Oups, there's nothing at this endpoint : "
	        + ex.getRequestURL();
	ApiError apiError = new ApiError(ex.getMessage(), error);
	return handleExceptionInternal(ex, apiError, null, HttpStatus.NOT_FOUND,
	        request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
	String error = "JsonParseError";
	ApiError apiError = new ApiError(ex.getMessage(), error);
	return handleExceptionInternal(ex, apiError, null,
	        HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
	ApiError apiError = new ApiError("Resource not found", ex.getMessage());
	return super.handleExceptionInternal(ex, apiError, null,
	        HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> accesDenied(AccessDeniedException ex,
            WebRequest request) {
	ApiError apiError = new ApiError("Acces restricted", ex.getMessage());
	return handleExceptionInternal(ex, apiError, null, HttpStatus.FORBIDDEN,
	        request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex,
            WebRequest request) {
	String message = "Unexpected error";
	ApiError apiError = new ApiError(message, ex.getMessage());
	LOGGER.error(message, ex);
	return handleExceptionInternal(ex, apiError, null,
	        HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
	List<String> errors = new ArrayList<>();
	for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	    errors.add(violation.getRootBeanClass().getName() + " "
	            + violation.getPropertyPath() + ": "
	            + violation.getMessage());
	}
	ApiError apiError = new ApiError(ex.getMessage(), errors);
	return handleExceptionInternal(ex, apiError, null,
	        HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleSqlConstraintViolation(
            DataIntegrityViolationException ex, WebRequest request) {
	ApiError apiError = new ApiError(ex.getMessage(),
	        ex.getCause().getCause().getMessage());
	return handleExceptionInternal(ex, apiError, null, HttpStatus.CONFLICT,
	        request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
            Object body, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
	LOGGER.error("Error(" + status + ") :", ex);
	return super.handleExceptionInternal(ex, body, headers, status,
	        request);
    }
}
