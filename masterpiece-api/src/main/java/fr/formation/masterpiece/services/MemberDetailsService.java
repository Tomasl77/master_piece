package fr.formation.masterpiece.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.masterpiece.domain.dtos.views.MemberInfoDto;

public interface MemberDetailsService extends UserDetailsService {

    MemberInfoDto getCurrentUserInfo(Long id);
}
