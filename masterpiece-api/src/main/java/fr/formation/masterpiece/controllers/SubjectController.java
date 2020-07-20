package fr.formation.masterpiece.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.services.SubjectManagerService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectManagerService service;

    public SubjectController(SubjectManagerService service) {
	this.service = service;
    }

    @PostMapping
    public void postSubject(@Valid @RequestBody SubjectDto dto) {
	service.create(dto);
    }
}