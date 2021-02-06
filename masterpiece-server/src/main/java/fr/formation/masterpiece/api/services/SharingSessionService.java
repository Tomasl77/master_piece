package fr.formation.masterpiece.api.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;

import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionViewDto;

/**
 * Service to handle {@code SharingSession} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface SharingSessionService {

    /**
     * Persists given {@code SharingSession}.
     *
     * @param dto the dto holding informations
     * @throws MessagingException a messaging exception
     */
    void create(SharingSessionCreateDto dto) throws MessagingException;

    /**
     * Get a {@code List} of all active {@code SharingSession}
     *
     * @return a {@code List} of {@code SharingSessionViewDto}
     */
    List<SharingSessionViewDto> getAllSessions();

    /**
     * Retrieves wether or not a sharing session is already scheduled this day
     * <p>
     *
     * Used for {@code NotSameDay} validation
     *
     * @param date the {@code LocalDateTime} to check
     * @return {@code true} if a session is not scheduled the same day,
     *         {@code false} otherwise
     */
    boolean isDateValid(LocalDateTime date);
}
