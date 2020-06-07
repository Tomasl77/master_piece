package fr.formation.masterpiece.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.domain.dtos.AccountRegisterDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.AccountViewDto;
import fr.formation.masterpiece.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
	this.service = service;
    }

    @GetMapping("/{id}")
    public AccountViewDto getOne(@PathVariable("id") Long id) {
	return service.getOne(id);
    }

    @GetMapping("/{username}/verify")
    public UsernameCheckDto checkUsername(
            @PathVariable("username") String username) {
	return service.checkUsername(username);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> create(
            @Valid @RequestBody AccountRegisterDto dto) {
	try {
	    service.create(dto);
	    return new ResponseEntity<>(
	            dto.getUsername() + "'s account created ! ",
	            HttpStatus.CREATED);
	} catch (Exception e) {
	    return new ResponseEntity<>("Something went wrong !",
	            HttpStatus.EXPECTATION_FAILED);
	}
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") Long id) {
	service.deleteOne(id);
    }
}
