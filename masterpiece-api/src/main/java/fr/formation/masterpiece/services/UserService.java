package fr.formation.masterpiece.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;

public interface UserService {

    boolean isValid(String username);

    CustomUserViewDto getOne(Long id);

    UsernameCheckDto checkUsername(String username);

    void deleteOne(Long id);

    List<CustomUserViewDto> getAll();

    CustomUserDto create(CustomUserCreateDto dto);
}
