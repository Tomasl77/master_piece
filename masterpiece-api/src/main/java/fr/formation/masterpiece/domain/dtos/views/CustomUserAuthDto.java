package fr.formation.masterpiece.domain.dtos.views;

import java.util.Set;

import fr.formation.masterpiece.domain.entities.Role;

/**
 * A projection of a {@code CustomUser} for authentication.
 */
public interface CustomUserAuthDto {

    Long getId();

    String getUsername();

    String getPassword();

    Set<Role> getRoles();

    boolean isEnabled();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();
}
