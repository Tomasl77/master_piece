package fr.formation.masterpiece.services;

import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserInfoDto;

public interface AccountService {

    boolean isValid(String username);

    void create(CustomUserCreateDto dto);

    CustomUserInfoDto getOne(Long id);

    UsernameCheckDto checkUsername(String username);

    void deleteOne(Long id);
}
