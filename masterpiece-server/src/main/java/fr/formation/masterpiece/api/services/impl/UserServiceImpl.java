package fr.formation.masterpiece.api.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.api.repositories.CustomUserRepository;
import fr.formation.masterpiece.api.repositories.RoleRepository;
import fr.formation.masterpiece.api.repositories.SubjectRepository;
import fr.formation.masterpiece.api.repositories.UserProfileRepository;
import fr.formation.masterpiece.api.services.UserService;
import fr.formation.masterpiece.commons.config.AbstractService;
import fr.formation.masterpiece.commons.exceptions.ResourceNotFoundException;
import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.CustomUserPatchDto;
import fr.formation.masterpiece.domain.dtos.UpdateCustomUserDto;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.domain.entities.CustomUser;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.security.SecurityHelper;

@Service
public class UserServiceImpl extends AbstractService implements UserService {

    private final CustomUserRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    private final RoleRepository roleRepository;

    private final SubjectRepository subjectRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(CustomUserRepository userRepository,
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
    public CustomUserDto create(CustomUserCreateDto dto) {
	String encodedPassword = passwordEncoder.encode(dto.getPassword());
	Set<Role> role = new HashSet<>();
	role.add(roleRepository.findByDefaultRole(true));
	CustomUser user = new CustomUser(encodedPassword, dto.getUsername(),
	        role, true, true, true, true, dto.getEmail());
	CustomUser savedUser = userRepository.save(user);
	CustomUserDto dtoToReturn = modelMapper.map(savedUser,
	        CustomUserDto.class);
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
    public CustomUserViewDto getOne(Long id) {
	CustomUser userProfile = userRepository.findById(id).orElseThrow(
	        () -> new ResourceNotFoundException("User not found"));
	return convert(userProfile, CustomUserViewDto.class);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
	CustomUser deleted = userRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException(
	                "Account not found : " + id));
	subjectRepository.deleteSubjectsAssociatedToUser(id);
	userRepository.delete(deleted);
    }

    @Override
    public List<CustomUserViewDto> getAll() {
	List<CustomUser> users = userRepository.findAll();
	return convertList(users, CustomUserViewDto.class);
    }

    @Override
    public UpdateCustomUserDto update(CustomUserPatchDto userDto) {
	Long userId = SecurityHelper.getUserId();
	CustomUser actualUser = userRepository.findById(userId).orElseThrow(
	        () -> new ResourceNotFoundException("No account found"));
	merge(userDto, actualUser);
	CustomUser savedUser = userRepository.save(actualUser);
	return convert(savedUser, UpdateCustomUserDto.class);
    }

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
