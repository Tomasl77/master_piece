package fr.formation.masterpiece.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.UpdateUserInfoDto;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;

public interface UserService {

    boolean isValid(String username);

    boolean isEmailValid(String email);

    CustomUserViewDto getOne(Long id);

    UsernameCheckDto checkUsername(String username);

    void deleteOne(Long id);

    List<CustomUserViewDto> getAll();

    CustomUserDto create(CustomUserCreateDto dto);

    void update(UpdateUserInfoDto user);

    UserEmailCheckDto checkEmail(String email);
}
