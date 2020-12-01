package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.CustomUserPatchDto;
import fr.formation.masterpiece.domain.dtos.UpdateCustomUserDto;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;

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

    UpdateCustomUserDto update(CustomUserPatchDto userDto);
}
