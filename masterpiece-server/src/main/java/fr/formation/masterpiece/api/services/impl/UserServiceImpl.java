package fr.formation.masterpiece.api.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.masterpiece.api.repositories.EntityUserRepository;
import fr.formation.masterpiece.api.repositories.RoleRepository;
import fr.formation.masterpiece.api.services.UserService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.users.EntityCustomUserDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserCreateDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserPatchDto;
import fr.formation.masterpiece.domain.dtos.users.EntityUserViewDto;
import fr.formation.masterpiece.domain.entities.EntityUser;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.security.SecurityHelper;

/**
 * Default concrete implementation of {@link UserService}
 *
 * @author Tomas LOBGEOIS
 *
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {

    private final EntityUserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(EntityUserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
	this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EntityUserDto create(EntityUserCreateDto dto) {
	String encodedPassword = passwordEncoder.encode(dto.getPassword());
	Set<Role> role = new HashSet<>();
	role.add(roleRepository.findByDefaultRole(true));
	EntityUser user = new EntityUser(encodedPassword, dto.getUsername(),
	        role, true, true, true, true, dto.getEmail());
	EntityUser savedUser = userRepository.save(user);
	EntityUserDto dtoToReturn = convert(savedUser, EntityUserDto.class);
	return dtoToReturn;
    }

    @Override
    public boolean isUsernameValid(String username) {
	return !userRepository.existsByUsername(username);
    }

    @Override
    public UsernameCheckDto checkUsername(String username) {
	boolean isValid = this.isUsernameValid(username);
	return new UsernameCheckDto(isValid);
    }

    @Override
    public EntityUserViewDto getOne(Long id) {
	EntityUser userProfile = userRepository.findById(id).orElseThrow(
	        () -> new ResourceNotFoundException("User not found"));
	return convert(userProfile, EntityUserViewDto.class);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
	userRepository.deActivate(id);
    }

    /**
     * Get all users with enable account
     *
     */
    @Override
    public List<EntityUserViewDto> getAll() {
	return userRepository.findAllByEnabled(true);
    }

    /**
     * Update an existing user with {@code EntityUserPatchDto}
     *
     * @param userDto the dto with information to update
     * @return a dto with information updated
     *
     */
    @Override
    @Transactional
    public EntityCustomUserDto update(EntityUserPatchDto userDto) {
	Long userId = SecurityHelper.getUserId();
	EntityUser actualUser = userRepository.findById(userId).orElseThrow(
	        () -> new ResourceNotFoundException("No account found"));
	merge(userDto, actualUser);
	EntityUser savedUser = userRepository.save(actualUser);
	return convert(savedUser, EntityCustomUserDto.class);
    }

    /**
     * Check if an email is available
     *
     * @param email the email to test availability
     * @return true if available, false if not
     *
     */
    @Override
    public boolean isEmailValid(String email) {
	return !userRepository.existsByEmail(email);
    }

    @Override
    public UserEmailCheckDto checkEmail(String email) {
	boolean isValid = this.isEmailValid(email);
	return new UserEmailCheckDto(isValid);
    }
}
