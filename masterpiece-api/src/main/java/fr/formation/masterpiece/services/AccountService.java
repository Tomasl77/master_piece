package fr.formation.masterpiece.services;

import fr.formation.masterpiece.domain.dtos.AccountRegisterDto;

public interface AccountService {

    boolean existsByUsername(String username);

    void create(AccountRegisterDto dto);
}
