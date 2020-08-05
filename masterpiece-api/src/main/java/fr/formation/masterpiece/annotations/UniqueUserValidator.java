package fr.formation.masterpiece.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.masterpiece.services.CustomUserService;

public class UniqueUserValidator
        implements ConstraintValidator<UniqueUser, String> {

    private CustomUserService service;

    public UniqueUserValidator(CustomUserService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(String username,
            ConstraintValidatorContext context) {
	return service.isValid(username);
    }
}