package fr.formation.masterpiece.api.services;

import java.util.List;

import javax.mail.MessagingException;

import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.views.SharingSessionViewDto;

public interface SharingSessionService {

    SharingSessionViewDto create(SharingSessionCreateDto dto)
            throws MessagingException;

    List<SharingSessionViewDto> getAllSessions();

    void buildSessionMail(SharingSessionViewDto dtoToReturn)
            throws MessagingException;
}
