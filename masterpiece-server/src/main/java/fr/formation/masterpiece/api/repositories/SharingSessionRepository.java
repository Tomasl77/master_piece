package fr.formation.masterpiece.api.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.SharingSession;

/**
 * {@code JpaRepository} to handle {@code SharingSession} persistence.
 *
 * @author Tomas LOBGEOIS
 *
 */
@Repository
public interface SharingSessionRepository
        extends JpaRepository<SharingSession, Long> {

    /**
     * Retrieve a {@code List} of {@code SharingSession} who respect this both
     * following conditions :
     * <ul>
     * <li>The lecturer of {@code SharingSession} must have an enable
     * account</li>
     * <li>The requester of {@code Subject} must have an enable account</li>
     *
     * @return a {@code List} of {@code SharingSession}
     *
     * @author Tomas LOBGEOIS
     */
    @Query(JpqlQuery.SESSION_WITH_ENABLE_LECTURER)
    List<SharingSession> getAllSessionWithUserEnable();

    boolean existsByStartTimeBetween(LocalDateTime startOfDay,
            LocalDateTime endOfDay);
}
