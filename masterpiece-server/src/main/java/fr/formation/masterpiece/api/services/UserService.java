package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.UserProfilePatchDto;
import fr.formation.masterpiece.domain.dtos.UpdateUserProfileDto;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UserProfileCreateDto;
import fr.formation.masterpiece.domain.dtos.UserProfileDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.UserProfileViewDto;

public interface UserService {

    boolean isValid(String username);

    boolean isEmailValid(String email);

    UserProfileViewDto getOne(Long id);

    UsernameCheckDto checkUsername(String username);

    void deleteOne(Long id);

    List<UserProfileViewDto> getAll();

    UserProfileDto create(UserProfileCreateDto dto);

    UserProfilePatchDto update(UpdateUserProfileDto user);

    UserEmailCheckDto checkEmail(String email);
}
