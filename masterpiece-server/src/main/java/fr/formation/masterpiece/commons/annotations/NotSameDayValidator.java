package fr.formation.masterpiece.commons.annotations;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.masterpiece.api.services.SharingSessionService;

/**
 * Validator for {@link NotSameDay} constraint
 *
 * @author Tomas LOBGEOIS
 *
 */
public class NotSameDayValidator
        implements ConstraintValidator<NotSameDay, LocalDateTime> {

    private final SharingSessionService service;

    public NotSameDayValidator(SharingSessionService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(LocalDateTime date,
            ConstraintValidatorContext context) {
	boolean isDateValid = service.isDateValid(date);
	return isDateValid;
    }
}
