package fr.formation.masterpiece.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

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
import fr.formation.masterpiece.repositories.SubjectRepository;
import fr.formation.masterpiece.repositories.UserCredentialsRepository;
import fr.formation.masterpiece.repositories.UserProfileRepository;
import fr.formation.masterpiece.services.UserService;

@Service
public class UserServiceImpl extends AbstractService implements UserService {

    private final UserCredentialsRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    private final RoleRepository roleRepository;

    private final SubjectRepository subjectRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserCredentialsRepository userRepository,
            UserProfileRepository userProfileRepository,
            RoleRepository roleRepository, SubjectRepository subjectRepository,
            PasswordEncoder passwordEncoder) {
	this.userRepository = userRepository;
	this.userProfileRepository = userProfileRepository;
	this.roleRepository = roleRepository;
	this.subjectRepository = subjectRepository;
	this.passwordEncoder = passwordEncoder;
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
	Long userProfileId = userProfileRepository.getUserProfileIdByUserId(id);
	UserProfile userProfile = userProfileRepository.getById(userProfileId)
	        .orElseThrow(() -> new AccountNotFoundException(
	                "Account not found"));
	return convert(userProfile, UserProfileViewDto.class);
    }

    @Transactional(rollbackOn = { AccountNotFoundException.class })
    @Override
    public void deleteOne(Long id) {
	subjectRepository.deleteSubjectsAssociatedToUser(id);
	UserProfile deleted = userProfileRepository.findById(id)
	        .orElseThrow(() -> new AccountNotFoundException(
	                "Account not found : " + id));
	userProfileRepository.delete(deleted);
    }

    @Override
    public List<UserProfileViewDto> getAll() {
	List<UserProfile> users = userProfileRepository.getAllProjectedBy();
	return convertList(users, UserProfileViewDto.class);
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
	return convert(savedUser, UserProfilePatchDto.class);
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
