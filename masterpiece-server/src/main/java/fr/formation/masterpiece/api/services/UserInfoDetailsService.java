package fr.formation.masterpiece.api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.masterpiece.domain.dtos.users.EntityUserViewDto;

/**
 * Service to extends {@code UserDetailsService} and add some logic about
 * {@code EntityUser}
 *
 * @author Tomas LOBGEOIS
 */
public interface UserInfoDetailsService extends UserDetailsService {

    /**
     * Returns current authenticated {@code EntityUser}.
     *
     * @param id the identifier of currently authenticated {@code EntityUser}
     * @return {@code CustomUserViewInfo} representing a {@code EntityUser}
     */
    EntityUserViewDto getCurrentUserInfo(Long id);
}
