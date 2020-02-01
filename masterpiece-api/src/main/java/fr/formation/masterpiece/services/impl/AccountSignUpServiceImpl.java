package fr.formation.masterpiece.services.impl;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.domain.dtos.AccountSignUpDto;
import fr.formation.masterpiece.domain.entities.AccountSignUp;
import fr.formation.masterpiece.repositories.AccountSignUpRepository;
import fr.formation.masterpiece.services.AccountSignUpService;

@Service
public class AccountSignUpServiceImpl implements AccountSignUpService {

    private final AccountSignUpRepository repository;

    protected AccountSignUpServiceImpl(AccountSignUpRepository repo) {
	this.repository = repo;
    }

    @Override
    public void accountSign(AccountSignUpDto dto) {
	AccountSignUp account = new AccountSignUp();
	populateAndSave(dto, account);
    }

    private void populateAndSave(AccountSignUpDto dto, AccountSignUp account) {
	account.setUsername(dto.getUsername());
	account.setPassword(dto.getPassword());
	repository.save(account);
    }

    @Override
    public boolean existingUsernames(String username) {
	return repository.existsByUsername(username);
    }
}
