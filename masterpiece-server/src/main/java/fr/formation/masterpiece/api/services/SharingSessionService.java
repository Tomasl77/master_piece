package fr.formation.masterpiece.api.services;

import java.time.LocalDateTime;
import java.util.List;

import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionCreateDto;
import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionViewDto;
import fr.formation.masterpiece.domain.dtos.sharingsessions.SharingSessionViewDto2;

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
     * @return an {@code SharingSessionViewDto} encapsulating informations of
     *         the persisted {@code SharingSession}
     */
    SharingSessionViewDto create(SharingSessionCreateDto dto);

    /**
     * Get a {@code List} of all active {@code SharingSession}
     *
     * @return a {@code List} of {@code SharingSession}
     */
    List<SharingSessionViewDto> getAllSessions();

    List<SharingSessionViewDto2> getAllSessionsBis();

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
