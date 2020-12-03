package fr.formation.masterpiece.api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;

/**
 * Service to extends {@code UserDetailsService} and add some logic about
 * {@code CustomUser}
 *
 * @author Tomas LOBGEOIS
 */
public interface UserInfoDetailsService extends UserDetailsService {

    /**
     * Returns current authenticated {@code CustomUser}.
     *
     * @param id the identifier of currently authenticated {@code CustomUser}
     * @return {@code CustomUserViewInfo} representing a {@code CustomUser}
     */
    CustomUserViewDto getCurrentUserInfo(Long id);
}
