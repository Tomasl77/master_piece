package fr.formation.masterpiece.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Payload;

/**
 * Custom annotation to ensure email is unique
 *
 * @author Tomas LOBGEOIS
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface UniqueEmail {

    String message() default "Email already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
