package fr.formation.masterpiece.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.domain.dtos.MemberCreateDto;
import fr.formation.masterpiece.domain.dtos.MemberDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.MemberInfoViewDto;
import fr.formation.masterpiece.services.MemberService;

@RestController
@RequestMapping("/users")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
	this.service = service;
    }

    @GetMapping("/{id}")
    public MemberInfoViewDto getOne(@PathVariable("id") Long id) {
	return service.getOne(id);
    }

    @GetMapping("/{username}/verify")
    public UsernameCheckDto checkUsername(
            @PathVariable("username") String username) {
	return service.checkUsername(username);
    }

    @PostMapping
    public MemberDto create(@Valid @RequestBody MemberCreateDto dto) {
	return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
	service.deleteOne(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MemberInfoViewDto> getAll() {
	return service.getAll();
    }
}
