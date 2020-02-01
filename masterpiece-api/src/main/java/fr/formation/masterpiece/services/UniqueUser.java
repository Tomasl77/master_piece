package fr.formation.masterpiece.services;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import fr.formation.masterpiece.services.impl.UniqueUserValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UniqueUserValidator.class)
public @interface UniqueUser {

    String message() default "Username already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
