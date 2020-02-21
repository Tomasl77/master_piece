package fr.formation.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}
