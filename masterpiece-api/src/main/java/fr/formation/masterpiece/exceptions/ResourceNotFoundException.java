package fr.formation.masterpiece.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 7951415048115348383L;

    public ResourceNotFoundException() {
	//
    }

    public ResourceNotFoundException(String message) {
	super(message);
    }
}
