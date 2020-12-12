package fr.formation.masterpiece.api.services;

import java.util.List;

import fr.formation.masterpiece.domain.dtos.subjects.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithVote;

/**
 * Service to handle {@code Subject} logic.
 *
 * @author Tomas LOBGEOIS
 */
public interface SubjectService {

    /**
     * Persists given {@code Subject}.
     *
     * @param dto the dto holding informations
     * @return an {@code SubjectDto} encapsulating the title of the persisted
     *         {@code Subject}
     */
    SubjectDto create(SubjectCreateDto dto);

    /**
     * Delete a {@code Subject} by his id.
     * <p>
     *
     * @param id the given identifier of the {@code Subject} to delete
     */
    void deleteOne(Long id);

    /**
     * Get a {@code List} of all {@code Subject} not scheduled for a
     * {@code SharingSession}
     *
     * @return a {@code List} of {@code SubjectViewDto}
     */
    List<SubjectViewDto> getAllNotScheduled();

    List<SubjectViewDtoWithVote> getAllNotScheduledWithVote();
}
