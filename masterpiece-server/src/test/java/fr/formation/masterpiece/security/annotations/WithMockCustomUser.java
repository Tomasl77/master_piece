package fr.formation.masterpiece.security.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContext;

import fr.formation.masterpiece.config.WithMockCustomUserSecurityContextFactory;

@Documented
@Retention(RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    User user = new User("Tomas", "Toto", null);
}
