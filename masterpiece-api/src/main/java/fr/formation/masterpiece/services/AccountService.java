package fr.formation.masterpiece.services;

import fr.formation.masterpiece.domain.dtos.AccountRegisterDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;

public interface AccountService {

    boolean isValid(String username);

    void create(AccountRegisterDto dto);

    UsernameCheckDto checkUsername(String username);
}
