package fr.formation.masterpiece.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.annotations.HasRoleUser;
import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
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
    void create(SharingSessionCreateDto dto) {
    }
}
