package fr.formation.masterpiece.commons.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.masterpiece.api.services.UserService;

public class UniqueUserValidator
        implements ConstraintValidator<UniqueUser, String> {

    private UserService service;

    public UniqueUserValidator(UserService service) {
	this.service = service;
    }

    /**
     * Method to check if username is valid or already in use
     *
     * returns a boolean
     */
    @Override
    public boolean isValid(String username,
            ConstraintValidatorContext context) {
	return service.isValid(username);
    }
}