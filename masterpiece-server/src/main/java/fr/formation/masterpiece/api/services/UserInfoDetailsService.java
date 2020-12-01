package fr.formation.masterpiece.api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;

/**
 * Service to extends {@code UserDetailsService} and add some logic about
 * {@code CustomUser}
 *
 * @author Tomas LOBGEOIS
 */
public interface UserInfoDetailsService extends UserDetailsService {

    CustomUserViewDto getCurrentUserInfo(Long id);
}
