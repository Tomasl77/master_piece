package fr.formation.masterpiece.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.domain.dtos.MemberCreateDto;
import fr.formation.masterpiece.domain.dtos.MemberDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.MemberInfoViewDto;
import fr.formation.masterpiece.domain.entities.Member;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.domain.entities.UserAuth;
import fr.formation.masterpiece.exceptions.AccountNotFoundException;
import fr.formation.masterpiece.repositories.MemberRepository;
import fr.formation.masterpiece.repositories.RoleRepository;
import fr.formation.masterpiece.repositories.UserJpaRepository;
import fr.formation.masterpiece.services.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    private final UserJpaRepository userRepository;

    private final RoleRepository roleRepository;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    protected MemberServiceImpl(UserJpaRepository userRepo,
            RoleRepository roleRepository, MemberRepository memberRepository,
            PasswordEncoder passwordEncoder) {
	this.roleRepository = roleRepository;
	this.userRepository = userRepo;
	this.memberRepository = memberRepository;
	this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MemberDto create(MemberCreateDto dto) {
	String encodedPassword = passwordEncoder
	        .encode(dto.getUser().getPassword());
	Set<Role> role = new HashSet<>();
	role.add(roleRepository.findByDefaultRole(true));
	UserAuth userAuth = new UserAuth(encodedPassword,
	        dto.getUser().getUsername(), role, true, true, true, true);
	Member memberToSave = new Member(dto.getEmail(), userAuth);
	Member user = memberRepository.save(memberToSave);
	MemberDto dtos = mapper.map(user, MemberDto.class);
	return dtos;
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
    public MemberInfoViewDto getOne(Long id) {
	Optional<MemberInfoViewDto> value = memberRepository.getById(id);
	if (value.isPresent()) {
	    return value.get();
	} else {
	    throw new AccountNotFoundException("Id not found : " + id);
	}
    }

    @Override
    public void deleteOne(Long id) {
	Optional<MemberInfoViewDto> value = memberRepository.getById(id);
	if (value.isPresent()) {
	    memberRepository.deleteById(id);
	} else {
	    throw new AccountNotFoundException(
	            "Can't delete account " + id + " because it's not created");
	}
    }
}
