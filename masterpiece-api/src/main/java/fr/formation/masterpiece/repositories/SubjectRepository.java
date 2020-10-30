package fr.formation.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Modifying
    @Query("delete from Subject s where s.user.id = :id")
    void deleteSubjectsAssociatedToUser(@Param("id") Long id);

    List<Subject> findAllBySchedule(boolean isSheduled);
}
