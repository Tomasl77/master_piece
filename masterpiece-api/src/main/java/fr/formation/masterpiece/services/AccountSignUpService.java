package fr.formation.masterpiece.services;

import fr.formation.masterpiece.jobs.dtos.AccountSignUpDto;

public interface AccountSignUpService {

    boolean existingUsernames(String username);

    void accountSign(AccountSignUpDto dto);
}
