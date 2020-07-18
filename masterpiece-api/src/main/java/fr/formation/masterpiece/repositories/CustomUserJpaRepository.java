package fr.formation.masterpiece.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.masterpiece.domain.dtos.views.CustomUserAuthDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserInfoDto;
import fr.formation.masterpiece.domain.entities.CustomUser;

public interface CustomUserJpaRepository
        extends JpaRepository<CustomUser, Long> {

    /**
     * Retrieves a projected view of the {@code CustomUser} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<CustomUserAuthDto> findByUsername(String username);

    /**
     * Retrieves a projected view of the current authenticated
     * {@code CustomUser}.
     *
     * @param id user id
     * @return a projected view
     */
    Optional<CustomUserInfoDto> getById(Long id);

    boolean existsByUsername(String username);
}