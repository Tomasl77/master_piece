package fr.formation.masterpiece.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.api.services.SubjectService;
import fr.formation.masterpiece.commons.annotations.HasRoleAdmin;
import fr.formation.masterpiece.commons.annotations.HasRoleUser;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDto;

/**
 * a {@code RestController} to handle {@code Subject}
 *
 * @author Tomas LOBGEOIS
 *
 */
@RestController
@RequestMapping("/subjects")
@HasRoleUser
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
	this.service = service;
    }

    @PostMapping
    public SubjectDto postSubject(@Valid @RequestBody SubjectCreateDto dto) {
	return service.create(dto);
    }

    @DeleteMapping("/{id}")
    @HasRoleAdmin
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable Long id) {
	service.deleteOne(id);
    }

    @GetMapping
    public List<SubjectViewDto> getAllNotScheduled() {
	return service.getAllNotScheduled();
    }
}