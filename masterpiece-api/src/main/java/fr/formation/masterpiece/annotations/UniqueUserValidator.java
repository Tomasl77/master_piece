package fr.formation.masterpiece.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.masterpiece.services.UserService;

public class UniqueUserValidator
        implements ConstraintValidator<UniqueUser, String> {

    private UserService service;

    public UniqueUserValidator(UserService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(String username,
            ConstraintValidatorContext context) {
	return service.isValid(username);
    }
}