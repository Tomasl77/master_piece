package fr.formation.masterpiece.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.masterpiece.domain.dtos.users.CustomUserAuthDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;
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

    /**
     * Check if username is already store in database
     *
     * @param username the username to check
     * @return {@code true} if username already exists, {@code false} if not
     */
    boolean existsByUsername(String username);

    /**
     * Check if email is already store in database
     *
     * @param email the email to check
     * @return {@code true} if email already exists, {@code false} if not
     */
    boolean existsByEmail(String email);

    /**
     * Custom jpql query to de-activate an user
     *
     * @param id the id of user to de-activate
     *
     * @author Tomas LOBGEOIS
     */
    @Modifying
    @Query(JpqlQuery.DEACTIVATE_USER)
    void deActivate(@Param("userId") Long id);

    List<CustomUser> findAllByEnabled(boolean enabled);
}