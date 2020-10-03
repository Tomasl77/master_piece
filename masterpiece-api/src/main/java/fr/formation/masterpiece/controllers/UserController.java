package fr.formation.masterpiece.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.annotations.HasRoleUser;
import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.CustomUserDto;
import fr.formation.masterpiece.domain.dtos.UpdateUserInfoDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
	this.service = service;
    }

    @GetMapping("/{id}")
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

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
	service.deleteOne(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CustomUserViewDto> getAll() {
	return service.getAll();
    }

    @PutMapping("/{id}")
    @HasRoleUser
    public void update(@Valid @RequestBody UpdateUserInfoDto user) {
	service.update(user);
    }
}
