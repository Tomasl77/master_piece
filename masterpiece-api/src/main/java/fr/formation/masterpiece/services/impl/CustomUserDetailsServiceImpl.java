package fr.formation.masterpiece.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.security.CustomUserDetails;
import fr.formation.masterpiece.domain.dtos.views.CustomUserAuthDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserInfoDto;
import fr.formation.masterpiece.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.repositories.CustomUserJpaRepository;
import fr.formation.masterpiece.services.CustomUserDetailsService;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final CustomUserJpaRepository repo;

    protected CustomUserDetailsServiceImpl(CustomUserJpaRepository repo) {
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
    public CustomUserInfoDto getCurrentUserInfo(Long id) {
	return repo.getById(id).orElseThrow(
	        () -> new ResourceNotFoundException("with id:" + id));
    }
}