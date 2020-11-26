package fr.formation.masterpiece.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.masterpiece.domain.dtos.views.CustomUserAuthDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.domain.entities.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

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

    boolean existsByEmail(String email);

    /**
     * Custom jpql query to de-activate an user
     *
     * @param id the id of user to de-activate
     *
     * @author Tomas LOBGEOIS
     */
    @Modifying
    @Query("UPDATE CustomUser u SET u.enabled = false WHERE u.id = :userId")
    void deActivate(@Param("userId") Long id);

    List<CustomUser> findAllByEnabled(boolean enabled);
}