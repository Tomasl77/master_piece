package fr.formation.masterpiece.commons.annotations;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.masterpiece.api.services.SharingSessionService;

public class NotSameDayValidator
        implements ConstraintValidator<NotSameDay, LocalDateTime> {

    private final SharingSessionService service;

    public NotSameDayValidator(SharingSessionService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(LocalDateTime date,
            ConstraintValidatorContext context) {
	return service.isDateValid(date);
    }
}
