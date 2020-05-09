package fr.formation.masterpiece.services;

import fr.formation.masterpiece.domain.dtos.AccountRegisterDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.AccountViewDto;

public interface AccountService {

    boolean isValid(String username);

    void create(AccountRegisterDto dto);

    AccountViewDto getOne(Long id);

    UsernameCheckDto checkUsername(String username);
}
