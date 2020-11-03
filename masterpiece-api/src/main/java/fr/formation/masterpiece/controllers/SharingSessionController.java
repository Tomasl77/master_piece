package fr.formation.masterpiece.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.annotations.HasRoleUser;
import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.views.SharingSessionViewDto;
import fr.formation.masterpiece.services.SharingSessionService;

@RestController
@RequestMapping("/sharing-sessions")
@HasRoleUser
public class SharingSessionController {

    private final SharingSessionService sharingSessionService;

    public SharingSessionController(
            SharingSessionService sharingSessionService) {
	this.sharingSessionService = sharingSessionService;
    }

    @PostMapping
    void create(@RequestBody @Valid SharingSessionCreateDto dto) {
	this.sharingSessionService.create(dto);
    }

    @GetMapping
    List<SharingSessionViewDto> getAllSessions() {
	return sharingSessionService.getAllSessions();
    }
}
