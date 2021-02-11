package fr.formation.masterpiece.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.masterpiece.domain.dtos.users.EntityUserAuthDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserViewDto;
import fr.formation.masterpiece.domain.entities.EntityUser;

/**
 * {@link JpaRepository} to handle {@code EntityUser} persistence.
 */
public interface CustomUserRepository extends JpaRepository<EntityUser, Long> {

    /**
     * Retrieves a projected view of the {@code UserAuth} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<EntityUserAuthDto> findByUsername(String username);

    /**
     * Retrieves a projected view of the current authenticated {@code UserAuth}.
     *
     * @param id user id
     * @return a projected view
     */
    Optional<EntityUserViewDto> getById(Long id);

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
     */
    @Modifying
    @Query(JpqlQuery.DEACTIVATE_USER)
    void deActivate(@Param("userId") Long id);

    /**
     * Returns a {@code List} of {@code EntityUser} containing only the users
     * matching to the boolean
     *
     * @param enabled the boolean to match
     * @return a {@code List} of {@code EntityUser}
     *
     */
    @Query(JpqlQuery.FIND_USERS_BY_ENABLED)
    List<EntityUserViewDto> findAllByEnabled(boolean enabled);
}