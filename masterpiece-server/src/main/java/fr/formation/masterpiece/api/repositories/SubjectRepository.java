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
     * {@code CustomUser} requester must have an enable account
     *
     * @param isSheduled {@code true} to have all {@code Subject} already
     *                   scheduled, {@code false} to have all {@code Subject}
     *                   not scheduled
     * @return a {@code List} of {@code Subject}
     *
     * @author Tomas LOBGEOIS
     */
    List<Subject> findAllByScheduleAndRequesterEnabledTrue(boolean isSheduled);

    /**
     * Update a {@code Subject} when it has been choosen for a
     * {@code SharingSession}
     *
     * @param id the id of {@code Subject} to update
     *
     * @author Tomas LOBGEOIS
     */
    @Modifying
    @Query(JpqlQuery.SCHEDULE_SESSION)
    void setSessionScheduleTrue(@Param("subjectId") Long id);

    /**
     * Custom request to retrieve a {@code List} of {@code Subject} with the
     * number of vote associated to it
     *
     * @return a {@code List} of {@code SubjectViewDtoWithVote}
     */
    @Query(JpqlQuery.SUBJECT_WITH_NUMBER_OF_VOTES)
    List<SubjectViewDtoWithVote> findAllWithVotes();

    @Query("SELECT new fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithVote"
            + "(s.id, s.title, s.description, s.category.name, s.requester.username, count(v.id) as numberOfVote) "
            + "FROM Subject s LEFT JOIN s.voters v WHERE s.id = :subjectId GROUP BY s.id")
    SubjectViewDtoWithVote findSubjectWithVote(
            @Param("subjectId") Long subjectId);

    /**
     * Custom request to retrieve a {@code List} of {@code Subject}'s IDs that
     * the user already vote for
     *
     * @param userId the id of user to check
     * @return a {@code List} of {@code VoteSubjectDto}
     */
    @Query(JpqlQuery.USER_VOTED_SUBJECTS)
    List<VoteSubjectDto> findVoteByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "INSERT INTO user_vote_subject(subject_id, user_id) VALUES (:subjectId, :userId)",
            nativeQuery = true)
    void addVote(@Param("userId") Long userId,
            @Param("subjectId") Long subjectId);
}
