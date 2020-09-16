package fr.formation.masterpiece.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.masterpiece.domain.dtos.views.UserInfoViewDto;

public interface MemberDetailsService extends UserDetailsService {

    UserInfoViewDto getCurrentUserInfo(Long id);
}
