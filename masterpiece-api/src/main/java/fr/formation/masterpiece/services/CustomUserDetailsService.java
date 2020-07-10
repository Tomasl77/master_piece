package fr.formation.masterpiece.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.masterpiece.domain.dtos.views.CustomUserInfoDto;

public interface CustomUserDetailsService extends UserDetailsService {

    CustomUserInfoDto getCurrentUserInfo(Long id);
}
