package fr.formation.masterpiece.controllers;

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

import fr.formation.masterpiece.annotations.HasRoleAdmin;
import fr.formation.masterpiece.annotations.HasRoleUser;
import fr.formation.masterpiece.domain.dtos.UpdateUserProfileDto;
import fr.formation.masterpiece.domain.dtos.UserEmailCheckDto;
import fr.formation.masterpiece.domain.dtos.UserProfileCreateDto;
import fr.formation.masterpiece.domain.dtos.UserProfileDto;
import fr.formation.masterpiece.domain.dtos.UserProfilePatchDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.UserProfileViewDto;
import fr.formation.masterpiece.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
	this.service = service;
    }

    @GetMapping("/{id}")
    @HasRoleUser
    public UserProfileViewDto getOne(@PathVariable("id") Long id) {
	return service.getOne(id);
    }

    @GetMapping("/{username}/verify")
    public UsernameCheckDto checkUsername(
            @PathVariable("username") String username) {
	return service.checkUsername(username);
    }

    @PostMapping
    public UserProfileDto create(@Valid @RequestBody UserProfileCreateDto dto) {
	return service.create(dto);
    }

    @DeleteMapping("/delete/{id}")
    @HasRoleAdmin
    public void deleteAccount(@PathVariable("id") Long id) {
	service.deleteOne(id);
    }

    @GetMapping
    @HasRoleAdmin
    public List<UserProfileViewDto> getAll() {
	return service.getAll();
    }

    @PatchMapping("/update")
    @HasRoleUser
    public UserProfilePatchDto update(
            @Valid @RequestBody UpdateUserProfileDto user) {
	return service.update(user);
    }

    @GetMapping("/{email}/mail-verify")
    public UserEmailCheckDto checkEmail(@PathVariable("email") String email) {
	return service.checkEmail(email);
    }
}
