package fr.formation.masterpiece.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.security.CustomUserDetails;
import fr.formation.masterpiece.domain.dtos.views.CustomUserAuthDto;
import fr.formation.masterpiece.domain.dtos.views.UserInfoViewDto;
import fr.formation.masterpiece.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.repositories.UserJpaRepository;
import fr.formation.masterpiece.services.MemberDetailsService;

@Service
public class MemberDetailsServiceImpl implements MemberDetailsService {

    private final UserJpaRepository repo;

    protected MemberDetailsServiceImpl(UserJpaRepository repo) {
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
    public UserInfoViewDto getCurrentUserInfo(Long id) {
	return repo.getById(id).orElseThrow(
	        () -> new ResourceNotFoundException("with id:" + id));
    }
}