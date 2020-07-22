package fr.formation.masterpiece.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerExceptionHandler
        extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
	List<String> errors = new ArrayList<>();
	for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	    errors.add(error.getField() + ": " + error.getCode());
	}
	for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	    errors.add(error.getObjectName() + ": " + error.getCode());
	}
	ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
	        ex.getLocalizedMessage(), errors);
	return handleExceptionInternal(ex, apiError, headers,
	        apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
	String error = ex.getParameterName() + " parameter is missing";
	ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
	        ex.getLocalizedMessage(), error);
	return errorToReturn(apiError);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
	List<String> errors = new ArrayList<>();
	for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	    errors.add(violation.getRootBeanClass().getName() + " "
	            + violation.getPropertyPath() + ": "
	            + violation.getMessage());
	}
	ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
	        ex.getLocalizedMessage(), errors);
	return errorToReturn(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
	StringBuilder builder = new StringBuilder();
	builder.append(ex.getMethod());
	builder.append(
	        " method is not supported for this request. Supported methods are ");
	ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
	ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED,
	        ex.getLocalizedMessage(), builder.toString());
	return errorToReturn(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
	String error = "Oups, there's nothing at this endpoint : "
	        + ex.getRequestURL();
	ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
	        ex.getLocalizedMessage(), error);
	return errorToReturn(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
	String error = "JsonParseError";
	ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
	        ex.getMessage(), error);
	return errorToReturn(apiError);
    }

    @ExceptionHandler({ AccountNotFoundException.class })
    public ResponseEntity<Object> idNotFoundException(
            AccountNotFoundException ex) {
	ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(),
	        ex.getLocalizedMessage());
	return errorToReturn(apiError);
    }

    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<Object> handleAllExceptions(Exception ex,
            WebRequest request) {
	String message = "Unexpected error";
	ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
	        ex.getMessage(), message);
	return errorToReturn(apiError);
    }

    private ResponseEntity<Object> errorToReturn(ApiError apiError) {
	return new ResponseEntity<>(apiError, new HttpHeaders(),
	        apiError.getStatus());
    }
}
