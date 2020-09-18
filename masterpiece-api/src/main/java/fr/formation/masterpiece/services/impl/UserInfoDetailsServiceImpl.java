package fr.formation.masterpiece.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.security.CustomUserDetails;
import fr.formation.masterpiece.domain.dtos.views.CustomUserAuthDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.repositories.UserJpaRepository;
import fr.formation.masterpiece.services.UserInfoDetailsService;

@Service
public class UserInfoDetailsServiceImpl implements UserInfoDetailsService {

    private final UserJpaRepository repo;

    protected UserInfoDetailsServiceImpl(UserJpaRepository repo) {
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
    public CustomUserViewDto getCurrentUserInfo(Long id) {
	return repo.getById(id).orElseThrow(
	        () -> new ResourceNotFoundException("with id:" + id));
    }
}