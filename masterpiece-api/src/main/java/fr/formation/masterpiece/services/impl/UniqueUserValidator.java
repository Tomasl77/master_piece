package fr.formation.masterpiece.services.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.masterpiece.services.AccountService;
import fr.formation.masterpiece.services.UniqueUser;

public class UniqueUserValidator
        implements ConstraintValidator<UniqueUser, String> {

    private AccountService service;

    public UniqueUserValidator(AccountService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(String username,
            ConstraintValidatorContext context) {
	return !service.existsByUsername(username);
    }
}