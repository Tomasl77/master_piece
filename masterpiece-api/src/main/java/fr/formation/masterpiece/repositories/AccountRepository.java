package fr.formation.masterpiece.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.dtos.views.AccountViewDto;
import fr.formation.masterpiece.domain.entities.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {

    Optional<AccountViewDto> getById(Long id);

    boolean existsByUsername(String username);
}
