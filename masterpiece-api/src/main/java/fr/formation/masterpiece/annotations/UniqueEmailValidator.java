package fr.formation.masterpiece.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.masterpiece.services.UserService;

public class UniqueEmailValidator
        implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
	this.userService = userService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
	return userService.isEmailValid(email);
    }
}
