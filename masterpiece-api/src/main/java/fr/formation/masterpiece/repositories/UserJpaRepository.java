package fr.formation.masterpiece.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.masterpiece.domain.dtos.views.CustomUserAuthDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.domain.entities.CustomUser;

public interface UserJpaRepository extends JpaRepository<CustomUser, Long> {

    /**
     * Retrieves a projected view of the {@code UserAuth} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<CustomUserAuthDto> findByUsername(String username);

    /**
     * Retrieves a projected view of the current authenticated {@code UserAuth}.
     *
     * @param id user id
     * @return a projected view
     */
    Optional<CustomUserViewDto> getById(Long id);

    boolean existsByUsername(String username);

    List<CustomUserViewDto> getAllProjectedBy();

    Long findUserInfoIdById(Long id);
}