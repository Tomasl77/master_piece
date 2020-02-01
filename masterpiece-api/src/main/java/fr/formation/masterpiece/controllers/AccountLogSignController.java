package fr.formation.masterpiece.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.domain.dtos.AccountSignUpDto;
import fr.formation.masterpiece.services.AccountSignUpService;

@RestController
@RequestMapping(value = { "/create-account", "/checkusername" })
@CrossOrigin("*")
public class AccountLogSignController {

    private AccountSignUpService service;

    public AccountLogSignController(AccountSignUpService service) {
	this.service = service;
    }

    @GetMapping("/{username}")
    public boolean checkUsername(@PathVariable("username") String username) {
	return service.existingUsernames(username);
    }

    @PostMapping
    public void createAccount(@Valid @RequestBody AccountSignUpDto dto) {
	service.accountSign(dto);
    }
}
