package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserCreateDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserPatchDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserViewDto;
import fr.formation.masterpiece.domain.dtos.users.EntityCustomUserDto;

/**
 * Service to handle {@code EntityUser} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface UserService {

    /**
     * Retrieves wether or not given username exists in database
     * <p>
     *
     * Used for {@code UniqueUser} validation
     *
     * @param username the given username to test
     * @return {@code true} if the username not exists in database;
     *         {@code false} otherwise
     */
    boolean isUsernameValid(String username);

    /**
     * Retrieves wether or not given username exists in database
     * <p>
     *
     * Used for {@code UniqueEmail} validation
     *
     * @param email the given email to test
     * @return {@code true} if the email not exists in database; {@code false}
     *         otherwise
     */
    boolean isEmailValid(String email);

    EntityUserViewDto getOne(Long id);

    /**
     * The dto containing the {@code boolean} checking if a username is unique
     * in database
     *
     * @param username the username to check
     * @return a {@code UsernameCheckDto} encapsulating the boolean for
     *         {@link #isUsernameValid(String) isUsernameValid(String)}
     */
    UsernameCheckDto checkUsername(String username);

    /**
     * Delete a {@code EntityUser} by his id.
     * <p>
     *
     * @param id the given identifier of the {@code EntityUser} to delete
     */
    void deleteOne(Long id);

    /**
     * Get a {@code List} of all active {@code EntityUser}
     *
     * @return a {@code List} of {@code EntityUserViewDto}
     */
    List<EntityUserViewDto> getAll();

    /**
     * Persists given {@code EntityUser}.
     *
     * @param dto the dto holding informations
     * @return an {@code EntityUserDto} encapsulating the username and email of
     *         the persisted {@code EntityUser}
     */
    EntityUserDto create(EntityUserCreateDto dto);

    /**
     * The dto containing the {@code boolean} checking if an email is unique in
     * database
     *
     * @param email the email to check
     * @return a {@code UserEmailCheckDto} encapsulating the boolean for
     *         {@link #isEmailValid(String) method} isEmailValid(String)
     */
    UserEmailCheckDto checkEmail(String email);

    /**
     * Update a {@code EntityUser} email
     *
     * @param userDto the dto containing the new email
     * @return a {@code CustomUserUpdateDto} containing the new email
     */
    EntityCustomUserDto update(EntityUserPatchDto userDto);
}
