package fr.formation.masterpiece.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.api.services.UserService;
import fr.formation.masterpiece.commons.annotations.HasRoleAdmin;
import fr.formation.masterpiece.commons.annotations.HasRoleUser;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserPatchDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserUpdateDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
	this.service = service;
    }

    @GetMapping("/{id}")
    @HasRoleUser
    public CustomUserViewDto getOne(@PathVariable("id") Long id) {
	return service.getOne(id);
    }

    @GetMapping("/{username}/verify")
    public UsernameCheckDto checkUsername(
            @PathVariable("username") String username) {
	return service.checkUsername(username);
    }

    @PostMapping
    public CustomUserDto create(@Valid @RequestBody CustomUserCreateDto dto) {
	return service.create(dto);
    }

    @DeleteMapping("{id}")
    @HasRoleAdmin
    public void deleteAccount(@PathVariable("id") Long id) {
	service.deleteOne(id);
    }

    @GetMapping
    @HasRoleAdmin
    public List<CustomUserViewDto> getAll() {
	return service.getAll();
    }

    @PatchMapping
    @HasRoleUser
    public CustomUserUpdateDto update(
            @Valid @RequestBody CustomUserPatchDto user) {
	return service.update(user);
    }

    @GetMapping("/{email}/mail-verify")
    public UserEmailCheckDto checkEmail(@PathVariable("email") String email) {
	return service.checkEmail(email);
    }
}
