package fr.formation.masterpiece.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.AbstractService;
import fr.formation.masterpiece.config.security.SecurityHelper;
import fr.formation.masterpiece.domain.dtos.UpdateUserProfileDto;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UserProfileCreateDto;
import fr.formation.masterpiece.domain.dtos.UserProfileDto;
import fr.formation.masterpiece.domain.dtos.UserProfilePatchDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.UserProfileViewDto;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.domain.entities.UserCredentials;
import fr.formation.masterpiece.domain.entities.UserProfile;
import fr.formation.masterpiece.exceptions.AccountNotFoundException;
import fr.formation.masterpiece.repositories.RoleRepository;
import fr.formation.masterpiece.repositories.UserCredentialsRepository;
import fr.formation.masterpiece.repositories.UserProfileRepository;
import fr.formation.masterpiece.services.UserService;

@Service
public class UserServiceImpl extends AbstractService implements UserService {

    private final UserCredentialsRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    protected UserServiceImpl(UserCredentialsRepository userRepo,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder,
            UserProfileRepository userProfileRepository) {
	this.roleRepository = roleRepository;
	this.userRepository = userRepo;
	this.passwordEncoder = passwordEncoder;
	this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfileDto create(UserProfileCreateDto dto) {
	String encodedPassword = passwordEncoder
	        .encode(dto.getCredentials().getPassword());
	Set<Role> role = new HashSet<>();
	role.add(roleRepository.findByDefaultRole(true));
	UserProfile userProfile = new UserProfile(dto.getEmail());
	UserCredentials credentials = new UserCredentials(encodedPassword,
	        dto.getCredentials().getUsername(), role, true, true, true,
	        true);
	userProfile.setCredentials(credentials);
	UserProfile user = userProfileRepository.save(userProfile);
	UserProfileDto dtoToReturn = modelMapper.map(user,
	        UserProfileDto.class);
	return dtoToReturn;
    }

    @Override
    public boolean isValid(String username) {
	return !userRepository.existsByUsername(username);
    }

    @Override
    public UsernameCheckDto checkUsername(String username) {
	boolean isValid = this.isValid(username);
	return new UsernameCheckDto(isValid);
    }

    @Override
    public UserProfileViewDto getOne(Long id) {
	return userProfileRepository.getById(id).orElseThrow(
	        () -> new AccountNotFoundException("Id not found : " + id));
    }

    @Override
    public void deleteOne(Long id) {
	userProfileRepository.deleteById(id);
    }

    @Override
    public List<UserProfileViewDto> getAll() {
	return userProfileRepository.getAllProjectedBy();
    }

    @Override
    public UserProfilePatchDto update(UpdateUserProfileDto userDto) {
	Long userCredentialsId = SecurityHelper.getUserId();
	Long userId = userProfileRepository
	        .getUserProfileIdByUserId(userCredentialsId);
	UserProfile actualUser = userProfileRepository.findById(userId)
	        .orElseThrow(
	                () -> new AccountNotFoundException("No account found"));
	merge(userDto, actualUser);
	UserProfile savedUser = userProfileRepository.save(actualUser);
	return modelMapper.map(savedUser, UserProfilePatchDto.class);
    }

    @Override
    public boolean isEmailValid(String email) {
	return !userProfileRepository.existsByEmail(email);
    }

    @Override
    public UserEmailCheckDto checkEmail(String email) {
	boolean isValid = this.isEmailValid(email);
	return new UserEmailCheckDto(isValid);
    }
}
