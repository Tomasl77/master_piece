package fr.formation.masterpiece.api.services;

import java.util.List;

import javax.mail.MessagingException;

import fr.formation.masterpiece.domain.dtos.subjects.SubjectCreateDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDto;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithVote;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectVoteUpdateDto;
import fr.formation.masterpiece.domain.dtos.subjects.VotedSubjectByUserDto;

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
    SubjectViewDto create(SubjectCreateDto dto);

    /**
     * Delete a {@code Subject} by his id.
     * <p>
     *
     * @param id the given identifier of the {@code Subject} to delete
     */
    void deleteOne(Long id) throws MessagingException;

    /**
     * Get a {@code List} of all {@code Subject} not scheduled for a
     * {@code SharingSession}, with the number of vote associated to each of
     * them
     *
     * @return a {@code List} of {@code SubjectViewDtoWithVote}
     */
    List<SubjectViewDtoWithVote> getAllNotScheduledWithVote();

    /**
     * Get a {@code List} of all {@code Subject} a specific user has voted for.
     *
     * @param userId the id of the user
     * @return a {@code List} of {@code VotedSubjectByUserDto}
     */
    List<VotedSubjectByUserDto> getAllVotedSubjectByUserId();

    /**
     * Update the vote of a {@code EntityUser} for a specific subject. If the
     * {@code EntityUser} has already voted, it'll be remove from the
     * {@code List} of voters associated to the subject.
     *
     * @param voteDto   a {@code DTO} containing a {@code boolean} defining if
     *                  the user has voted
     * @param subjectId id of the {@code Subject}
     * @return a {@code SubjectViewDtoWithVote} containing the
     */
    SubjectViewDtoWithVote changeVote(SubjectVoteUpdateDto voteDto,
            Long subjectId);
}
