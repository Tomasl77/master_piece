package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserPatchDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserUpdateDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;

/**
 * Service to handle {@code CustomUser} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface UserService {

    boolean isValid(String username);

    boolean isEmailValid(String email);

    CustomUserViewDto getOne(Long id);

    UsernameCheckDto checkUsername(String username);

    void deleteOne(Long id);

    List<CustomUserViewDto> getAll();

    CustomUserDto create(CustomUserCreateDto dto);

    UserEmailCheckDto checkEmail(String email);

    CustomUserUpdateDto update(CustomUserPatchDto userDto);
}
