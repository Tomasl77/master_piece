package fr.formation.masterpiece.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public Map<String, Set<String>> createAccount(
            @Valid @RequestBody AccountSignUpDto dto, BindingResult result) {
	Map<String, Set<String>> errors = new HashMap<>();
	if (this.checkUsername(dto.getUsername())) {
	    errors.computeIfAbsent("username", key -> new HashSet<>())
	            .add("usernameTaken");
	}
	for (FieldError fieldError : result.getFieldErrors()) {
	    String code = fieldError.getCode();
	    String field = fieldError.getField();
	    if (code.equals("NotBlank") || code.equals("NotNull")) {
		errors.computeIfAbsent(field, key -> new HashSet<>())
		        .add("required");
	    } else if (code.equals("Email") && field.equals("email")) {
		errors.computeIfAbsent(field, key -> new HashSet<>())
		        .add("pattern");
	    } else if (code.equals("Size") && field.equals("username")) {
		if (dto.getUsername().length() < 2) {
		    errors.computeIfAbsent(field, key -> new HashSet<>())
		            .add("minlength");
		} else {
		    errors.computeIfAbsent(field, key -> new HashSet<>())
		            .add("maxlength");
		}
	    }
	}
	if (errors.isEmpty()) {
	    service.accountSign(dto);
	}
	return errors;
    }
}
