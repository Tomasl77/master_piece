package fr.formation.masterpiece.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.domain.dtos.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.CustomUserInfoDto;
import fr.formation.masterpiece.services.AccountService;

@RestController
@RequestMapping("/users")
public class CustomUserController {

    private AccountService service;

    public CustomUserController(AccountService service) {
	this.service = service;
    }

    @GetMapping("/{id}")
    public CustomUserInfoDto getOne(@PathVariable("id") Long id) {
	return service.getOne(id);
    }

    @GetMapping("/{username}/verify")
    public UsernameCheckDto checkUsername(
            @PathVariable("username") String username) {
	return service.checkUsername(username);
    }

    @PostMapping
    @ResponseBody
    public void create(@Valid @RequestBody CustomUserCreateDto dto) {
	service.create(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") Long id) {
	service.deleteOne(id);
    }
}
