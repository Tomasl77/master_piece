package fr.formation.masterpiece.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.SharingSession;

@Repository
public interface SharingSessionRepository
        extends JpaRepository<SharingSession, Long> {

    @Query("SELECT s FROM SharingSession s "
            + "JOIN Subject su ON s.subject = su.id "
            + "JOIN CustomUser cu ON su.user = cu.id "
            + "WHERE cu.enabled = true AND s.user.enabled = true")
    List<SharingSession> getAllSessionWithUserEnable();
}
