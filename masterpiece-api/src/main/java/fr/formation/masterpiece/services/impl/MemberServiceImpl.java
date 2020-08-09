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
import fr.formation.masterpiece.domain.dtos.views.MemberInfoDto;
import fr.formation.masterpiece.domain.entities.Member;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.exceptions.AccountNotFoundException;
import fr.formation.masterpiece.repositories.MemberJpaRepository;
import fr.formation.masterpiece.repositories.RoleRepository;
import fr.formation.masterpiece.services.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberJpaRepository memberRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    protected MemberServiceImpl(MemberJpaRepository repo,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
	this.roleRepository = roleRepository;
	this.memberRepository = repo;
	this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MemberDto create(MemberCreateDto dto) {
	String encodedPassword = passwordEncoder.encode(dto.getPassword());
	Set<Role> role = new HashSet<>();
	role.add(roleRepository.findByDefaultRole(true));
	Member member = new Member(encodedPassword, dto.getUsername(), role,
	        true, true, true, true);
	Member user = memberRepository.save(member);
	return mapper.map(user, MemberDto.class);
    }

    @Override
    public boolean isValid(String username) {
	return !memberRepository.existsByUsername(username);
    }

    @Override
    public UsernameCheckDto checkUsername(String username) {
	boolean valid = this.isValid(username);
	return new UsernameCheckDto(valid);
    }

    @Override
    public MemberInfoDto getOne(Long id) {
	Optional<MemberInfoDto> value = memberRepository.getById(id);
	if (value.isPresent()) {
	    return value.get();
	} else {
	    throw new AccountNotFoundException("Id not found : " + id);
	}
    }

    @Override
    public void deleteOne(Long id) {
	Optional<MemberInfoDto> value = memberRepository.getById(id);
	if (value.isPresent()) {
	    memberRepository.deleteById(id);
	} else {
	    throw new AccountNotFoundException(
	            "Can't delete account " + id + " because it's not created");
	}
    }
}
