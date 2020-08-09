package fr.formation.masterpiece.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.masterpiece.domain.dtos.views.CustomUserAuthDto;
import fr.formation.masterpiece.domain.dtos.views.MemberInfoDto;
import fr.formation.masterpiece.domain.entities.Member;

public interface MemberJpaRepository
        extends JpaRepository<Member, Long> {

    /**
     * Retrieves a projected view of the {@code CustomUser} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<CustomUserAuthDto> findByUsername(String username);

    /**
     * Retrieves a projected view of the current authenticated
     * {@code CustomUser}.
     *
     * @param id user id
     * @return a projected view
     */
    Optional<MemberInfoDto> getById(Long id);

    boolean existsByUsername(String username);
}