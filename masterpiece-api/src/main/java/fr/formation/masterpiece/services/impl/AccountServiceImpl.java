package fr.formation.masterpiece.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.formation.masterpiece.domain.dtos.AccountRegisterDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.domain.entities.User;
import fr.formation.masterpiece.repositories.AccountRepository;
import fr.formation.masterpiece.repositories.RoleRepository;
import fr.formation.masterpiece.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository userRepository;

    private final RoleRepository roleRepository;

    protected AccountServiceImpl(AccountRepository repo,
            RoleRepository roleRepository) {
	this.roleRepository = roleRepository;
	this.userRepository = repo;
    }

    @Override
    public void create(AccountRegisterDto dto) {
	User account = new User();
	populateAndSave(dto, account);
    }

    private void populateAndSave(AccountRegisterDto dto, User account) {
	Set<Role> role = new HashSet<>();
	role.add(roleRepository.findByDefaultRole(true));
	account.setUsername(dto.getUsername());
	account.setPassword(dto.getPassword());
	account.setRole(role);
	userRepository.save(account);
    }

    @Override
    public boolean isValid(String username) {
	return !userRepository.existsByUsername(username);
    }

    @Override
    public UsernameCheckDto checkUsername(String username) {
	boolean valid = this.isValid(username);
	return new UsernameCheckDto(valid);
    }
}
