package fr.formation.masterpiece.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import fr.formation.masterpiece.config.security.CustomUserDetails;
import fr.formation.masterpiece.security.annotations.WithMockCustomUser;

public class WithMockCustomUserSecurityContextFactory
        implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(
            WithMockCustomUser customUser) {
	SecurityContext context = SecurityContextHolder.createEmptyContext();
	CustomUserDetails principal = new CustomUserDetails(
	        WithMockCustomUser.user, 1L);
	Authentication auth = new UsernamePasswordAuthenticationToken(principal,
	        "password", principal.getAuthorities());
	context.setAuthentication(auth);
	return context;
    }
}
