package fr.formation.masterpiece.commons.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom annotation to ensure that only one {@code SharingSession} is
 * programmed by day
 *
 * @author Tomas LOBGEOIS
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = NotSameDayValidator.class)
public @interface NotSameDay {

    String message() default "There's already a session this day";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
