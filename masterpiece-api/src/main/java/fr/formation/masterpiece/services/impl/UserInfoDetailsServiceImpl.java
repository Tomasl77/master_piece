package fr.formation.masterpiece.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.security.CustomUserDetails;
import fr.formation.masterpiece.domain.dtos.views.CustomUserAuthDto;
import fr.formation.masterpiece.domain.dtos.views.UserCredentialsViewDto;
import fr.formation.masterpiece.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.repositories.UserCredentialsRepository;
import fr.formation.masterpiece.services.UserInfoDetailsService;

@Service
public class UserInfoDetailsServiceImpl implements UserInfoDetailsService {

    private final UserCredentialsRepository repo;

    protected UserInfoDetailsServiceImpl(UserCredentialsRepository repo) {
	this.repo = repo;
    }

    // Throws UsernameNotFoundException (Spring contract)
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
	CustomUserAuthDto user = repo.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException(
	                "no user found with username: " + username));
	return new CustomUserDetails(user);
    }

    // Throws ResourceNotFoundException (restful practice)
    @Override
    public UserCredentialsViewDto getCurrentUserInfo(Long id) {
	return repo.getById(id).orElseThrow(
	        () -> new ResourceNotFoundException("with id:" + id));
    }
}