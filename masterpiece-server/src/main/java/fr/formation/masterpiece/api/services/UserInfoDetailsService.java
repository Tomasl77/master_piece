package fr.formation.masterpiece.api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.masterpiece.domain.dtos.views.UserCredentialsViewDto;

public interface UserInfoDetailsService extends UserDetailsService {

    UserCredentialsViewDto getCurrentUserInfo(Long id);
}
