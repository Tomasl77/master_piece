package fr.formation.masterpiece.services.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.masterpiece.repositories.AccountSignUpRepository;
import fr.formation.masterpiece.services.UniqueUser;

public class UniqueUserValidator
        implements ConstraintValidator<UniqueUser, String> {

    private AccountSignUpRepository repository;

    public UniqueUserValidator(AccountSignUpRepository repository) {
	this.repository = repository;
    }

    @Override
    public boolean isValid(String username,
            ConstraintValidatorContext context) {
	return !repository.existsByUsername(username);
    }
}
