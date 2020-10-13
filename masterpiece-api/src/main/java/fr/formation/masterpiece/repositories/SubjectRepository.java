package fr.formation.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> getAllProjectedBy();
}
