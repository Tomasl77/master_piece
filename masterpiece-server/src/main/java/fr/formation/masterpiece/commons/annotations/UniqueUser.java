package fr.formation.masterpiece.commons.annotations;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom annotation to ensure {@code EntityUser}'s username is unique
 *
 * @author tomas
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UniqueUserValidator.class)
public @interface UniqueUser {

    String message() default "Username already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
