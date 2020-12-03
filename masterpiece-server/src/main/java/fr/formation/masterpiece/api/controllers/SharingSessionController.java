package fr.formation.masterpiece.api.controllers;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.api.services.SharingSessionService;
import fr.formation.masterpiece.commons.annotations.HasRoleUser;
import fr.formation.masterpiece.commons.utils.EmailManager;
import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.views.SharingSessionViewDto;

@RestController
@RequestMapping("/sharing-sessions")
@HasRoleUser
public class SharingSessionController {

    private final SharingSessionService sharingSessionService;

    private final EmailManager emailManager;

    public SharingSessionController(SharingSessionService sharingSessionService,
            EmailManager emailManager) {
	this.sharingSessionService = sharingSessionService;
	this.emailManager = emailManager;
    }

    @PostMapping
    void create(@RequestBody @Valid SharingSessionCreateDto dto)
            throws MessagingException {
	SharingSessionViewDto dtoToReturn = sharingSessionService.create(dto);
	if (dtoToReturn != null) {
	    emailManager.buildSessionMail(dtoToReturn);
	}
    }

    @GetMapping
    List<SharingSessionViewDto> getAllSessions() {
	return sharingSessionService.getAllSessions();
    }
}
