package fr.formation.masterpiece.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.Subject;

/**
 * {@code JpaRepository} to handle {@code Subject} persistence.
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
}
