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

import fr.formation.masterpiece.domain.dtos.SubjectDto;
import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;
import fr.formation.masterpiece.services.SubjectService;

@RestController
@RequestMapping("/subjects")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
	this.service = service;
    }

    @PostMapping
    public void postSubject(@Valid @RequestBody SubjectDto dto) {
	service.create(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSubject(@PathVariable Long id) {
	service.delete(id);
    }

    @GetMapping
    public List<SubjectViewDto> getAll() {
	return service.getAll();
    }
}