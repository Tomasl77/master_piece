package fr.formation.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
