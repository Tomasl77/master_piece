package fr.formation.masterpiece.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.domain.dtos.AccountRegisterDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
	this.service = service;
    }

    @GetMapping("/{username}/verify")
    public UsernameCheckDto checkUsername(
            @PathVariable("username") String username) {
	return service.checkUsername(username);
    }

    @PostMapping
    public void create(@Valid @RequestBody AccountRegisterDto dto) {
	service.create(dto);
    }
}
