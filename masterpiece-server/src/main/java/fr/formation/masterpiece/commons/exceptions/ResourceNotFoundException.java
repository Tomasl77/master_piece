package fr.formation.masterpiece.commons.exceptions;

/**
 * Exception thrown if resource is not found.
 * <p>
 * Used to handle 404 Http error.
 *
 * @author Tomas LOBGEOIS
 *
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7951415048115348383L;

    /**
     * Public empty no-arg constructor calling {@link RuntimeException}
     * constructor.
     */
    public ResourceNotFoundException() {
	super();
    }

    /**
     * Public constructor calling {@link RuntimeException} constructor with
     * given message.
     * 
     * @param message the message given when throwing the exception
     */
    public ResourceNotFoundException(String message) {
	super(message);
    }
}
