package fr.formation.masterpiece.services.impl;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.domain.dtos.AccountRegisterDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.entities.Account;
import fr.formation.masterpiece.repositories.AccountRepository;
import fr.formation.masterpiece.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    protected AccountServiceImpl(AccountRepository repo) {
	this.repository = repo;
    }

    @Override
    public void create(AccountRegisterDto dto) {
	Account account = new Account();
	populateAndSave(dto, account);
    }

    private void populateAndSave(AccountRegisterDto dto, Account account) {
	account.setUsername(dto.getUsername());
	account.setPassword(dto.getPassword());
	repository.save(account);
    }

    @Override
    public boolean isValid(String username) {
	return !repository.existsByUsername(username);
    }

    @Override
    public UsernameCheckDto checkUsername(String username) {
	boolean valid = this.isValid(username);
	return new UsernameCheckDto(valid);
    }
}
