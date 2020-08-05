package fr.formation.masterpiece.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserInfoDto;
import fr.formation.masterpiece.domain.entities.CustomUser;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.exceptions.AccountNotFoundException;
import fr.formation.masterpiece.repositories.CustomUserJpaRepository;
import fr.formation.masterpiece.repositories.RoleRepository;
import fr.formation.masterpiece.services.CustomUserService;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    private final CustomUserJpaRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    protected CustomUserServiceImpl(CustomUserJpaRepository repo,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
	this.roleRepository = roleRepository;
	this.userRepository = repo;
	this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomUserDto create(CustomUserCreateDto dto) {
	String encodedPassword = passwordEncoder.encode(dto.getPassword());
	Set<Role> role = new HashSet<>();
	role.add(roleRepository.findByDefaultRole(true));
	CustomUser account = new CustomUser(encodedPassword, dto.getUsername(),
	        role, true);
	CustomUser user = userRepository.save(account);
	return mapper.map(user, CustomUserDto.class);
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

    @Override
    public CustomUserInfoDto getOne(Long id) {
	Optional<CustomUserInfoDto> value = userRepository.getById(id);
	if (value.isPresent()) {
	    return value.get();
	} else {
	    throw new AccountNotFoundException("Id not found : " + id);
	}
    }

    @Override
    public void deleteOne(Long id) {
	Optional<CustomUserInfoDto> value = userRepository.getById(id);
	if (value.isPresent()) {
	    userRepository.deleteById(id);
	} else {
	    throw new AccountNotFoundException(
	            "Can't delete account " + id + " because it's not created");
	}
    }
}
