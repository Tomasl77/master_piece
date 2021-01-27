package fr.formation.masterpiece.api.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.api.repositories.CustomUserRepository;
import fr.formation.masterpiece.api.services.UserInfoDetailsService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.domain.dtos.users.EntityUserAuthDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserViewDto;
import fr.formation.masterpiece.security.EntityUserDetails;

/**
 * Default concrete implementation of {@link UserInfoDetailsService}
 *
 */
@Service
public class UserInfoDetailsServiceImpl implements UserInfoDetailsService {

    private final CustomUserRepository repo;

    protected UserInfoDetailsServiceImpl(CustomUserRepository repo) {
	this.repo = repo;
    }

    // Throws UsernameNotFoundException (Spring contract)
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
	EntityUserAuthDto user = repo.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException(
	                "no user found with username: " + username));
	return new EntityUserDetails(user);
    }

    @Override
    public EntityUserViewDto getCurrentUserInfo(Long id) {
	return repo.getById(id).orElseThrow(
	        () -> new ResourceNotFoundException("User not found :" + id));
    }
}