package fr.formation.masterpiece.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithVote;
import fr.formation.masterpiece.domain.dtos.subjects.VoteSubjectDto;
import fr.formation.masterpiece.domain.entities.Subject;

/**
 * {@link JpaRepository} to handle {@code Subject} persistence.
 *
 * @author Tomas LOBGEOIS
 *
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    /**
     * Retrieve a {@code List} of {@code Subject} with the following condition:
     * <p>
     * {@code EntityUser} requester must have an enable account
     *
     * @param isSheduled {@code true} to have all {@code Subject} already
     *                   scheduled, {@code false} to have all {@code Subject}
     *                   not scheduled
     * @return a {@code List} of {@code Subject}
     *
     */
    List<Subject> findAllByScheduleAndRequesterEnabledTrue(boolean isSheduled);

    /**
     * Update a {@code Subject} when it has been choosen for a
     * {@code SharingSession}
     *
     * @param id the id of {@code Subject} to update
     */
    @Modifying
    @Query(JpqlQuery.SCHEDULE_SESSION)
    void setSessionScheduleTrue(@Param("subjectId") Long id);

    /**
     * Custom request to retrieve a {@code List} of {@code Subject} with the
     * number of vote associated to it
     *
     * @return a {@code List} of {@code SubjectViewDtoWithVote}
     *
     */
    @Query(JpqlQuery.SUBJECTS_WITH_NUMBER_OF_VOTES)
    List<SubjectViewDtoWithVote> findAllWithVotes();

    /**
     * Custom request to retrieve a {@code Subject} with the number of vote
     * associated to it
     *
     * @return a {@code SubjectViewDtoWithVote}
     *
     */
    @Query(JpqlQuery.SUBJECT_WITH_NUMBER_OF_VOTES)
    SubjectViewDtoWithVote findSubjectWithVote(
            @Param("subjectId") Long subjectId);

    /**
     * Custom request to retrieve a {@code List} of {@code Subject}'s IDs that
     * the user already vote for
     *
     * @param userId the id of user to check
     * @return a {@code List} of {@code VoteSubjectDto}
     *
     */
    @Query(JpqlQuery.USER_VOTED_SUBJECTS)
    List<VoteSubjectDto> findVoteByUserId(@Param("userId") Long userId);
}
