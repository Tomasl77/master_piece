package fr.formation.masterpiece.api.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;

import fr.formation.masterpiece.domain.dtos.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.views.SharingSessionViewDto;

/**
 * Service to handle {@code SharingSession} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface SharingSessionService {

    SharingSessionViewDto create(SharingSessionCreateDto dto)
            throws MessagingException;

    List<SharingSessionViewDto> getAllSessions();

    boolean isDateValid(LocalDateTime date);
}
