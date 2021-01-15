package fr.formation.masterpiece.commons.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom annotation to ensure {@code EntityUser}'s email is unique
 *
 * @author Tomas LOBGEOIS
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    String message() default "Email already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
