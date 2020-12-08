package fr.formation.masterpiece.commons.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Custom annotation to encapsulate Role and manage <code>@PreAuthorized</code>
 * {@code Role.ROLE_ADMIN}.
 * <p>
 *
 * @author Tomas LOBGEOIS
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@PreAuthorize("hasRole('ROLE_ADMIN')")
public @interface HasRoleAdmin {
    // Encapsulation for a role admin
}
