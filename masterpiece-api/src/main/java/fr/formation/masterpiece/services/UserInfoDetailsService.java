package fr.formation.masterpiece.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;

public interface UserInfoDetailsService extends UserDetailsService {

    CustomUserViewDto getCurrentUserInfo(Long id);
}
