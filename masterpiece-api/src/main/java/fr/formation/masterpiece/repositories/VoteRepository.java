package fr.formation.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
