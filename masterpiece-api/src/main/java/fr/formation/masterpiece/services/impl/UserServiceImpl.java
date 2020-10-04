package fr.formation.masterpiece.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.config.security.SecurityHelper;
import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.UpdateUserInfoDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.domain.entities.CustomUser;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.domain.entities.UserInfo;
import fr.formation.masterpiece.exceptions.AccountNotFoundException;
import fr.formation.masterpiece.repositories.RoleRepository;
import fr.formation.masterpiece.repositories.UserJpaRepository;
import fr.formation.masterpiece.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    protected UserServiceImpl(UserJpaRepository userRepo,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
	this.roleRepository = roleRepository;
	this.userRepository = userRepo;
	this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomUserDto create(CustomUserCreateDto dto) {
	String encodedPassword = passwordEncoder.encode(dto.getPassword());
	Set<Role> role = new HashSet<>();
	role.add(roleRepository.findByDefaultRole(true));
	UserInfo userInfo = new UserInfo(dto.getUserInfo().getEmail());
	CustomUser userAuth = new CustomUser(encodedPassword, dto.getUsername(),
	        role, true, true, true, true, userInfo);
	CustomUser user = userRepository.save(userAuth);
	CustomUserDto dtoToReturn = mapper.map(user, CustomUserDto.class);
	return dtoToReturn;
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
    public CustomUserViewDto getOne(Long id) {
	Optional<CustomUserViewDto> value = userRepository.getById(id);
	if (value.isPresent()) {
	    return value.get();
	} else {
	    throw new AccountNotFoundException("Id not found : " + id);
	}
    }

    @Override
    public void deleteOne(Long id) {
	Optional<CustomUserViewDto> value = userRepository.getById(id);
	if (value.isPresent()) {
	    userRepository.deleteById(id);
	} else {
	    throw new AccountNotFoundException("Invalid id : " + id);
	}
    }

    @Override
    public List<CustomUserViewDto> getAll() {
	return userRepository.getAllProjectedBy();
    }

    @Override
    public void update(UpdateUserInfoDto userDto) {
	Long userId = SecurityHelper.getUserId();
	CustomUser actualUser = userRepository.findById(userId).get();
	UserInfo infoToUpdate = mapper.map(userDto, UserInfo.class);
	actualUser.setInfo(infoToUpdate);
	userRepository.save(actualUser);
    }
}
