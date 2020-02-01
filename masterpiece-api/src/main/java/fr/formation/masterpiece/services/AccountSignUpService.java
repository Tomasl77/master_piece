package fr.formation.masterpiece.services;

import fr.formation.masterpiece.domain.dtos.AccountSignUpDto;

public interface AccountSignUpService {

    boolean existingUsernames(String username);

    void accountSign(AccountSignUpDto dto);
}
