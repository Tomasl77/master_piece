package fr.formation.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.jobs.entities.AccountSignUp;

@Repository
public interface AccountSignUpRepository
        extends JpaRepository<AccountSignUp, Long> {

    Boolean findByUsername(String username);
}
