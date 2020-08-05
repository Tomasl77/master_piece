package fr.formation.masterpiece.services;

import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserInfoDto;

public interface CustomUserService {

    boolean isValid(String username);

    CustomUserDto create(CustomUserCreateDto dto);

    CustomUserInfoDto getOne(Long id);

    UsernameCheckDto checkUsername(String username);

    void deleteOne(Long id);
}
