package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserPatchDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;
import fr.formation.masterpiece.domain.dtos.users.UpdateCustomUserDto;

/**
 * Service to handle {@code CustomUser} logic.
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

    CustomUserViewDto getOne(Long id);

    /**
     * The dto containing the {@code boolean} checking if a username is unique
     * in database
     *
     * @param username the username to check
     * @return a {@code UsernameCheckDto} encapsulating the boolean for
     *         {@link #isUsernameValid(String) method} isUsernameValid(String)
     */
    UsernameCheckDto checkUsername(String username);

    /**
     * Delete a {@code CustomUser} by his id.
     * <p>
     *
     * @param id the given identifier of the {@code CustomUser} to delete
     */
    void deleteOne(Long id);

    /**
     * Get a {@code List} of all active {@code CustomUser}
     *
     * @return a {@code List} of {@code CustomUserViewDto}
     */
    List<CustomUserViewDto> getAll();

    /**
     * Persists given {@code CustomUser}.
     *
     * @param dto the dto holding informations
     * @return an {@code CustomUserDto} encapsulating the username and email of
     *         the persisted {@code CustomUser}
     */
    CustomUserDto create(CustomUserCreateDto dto);

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
     * Update a {@code CustomUser} email
     *
     * @param userDto the dto containing the new email
     * @return a {@code CustomUserUpdateDto} containing the new email
     */
    UpdateCustomUserDto update(CustomUserPatchDto userDto);
}
