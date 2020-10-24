package fr.formation.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.SharingSession;

@Repository
public interface SharingSessionRepository
        extends JpaRepository<SharingSession, Long> {
}
