package fr.formation.masterpiece.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.Role;

/**
 * {@link JpaRepository} to handle {@code Role}.
 *
 * @author Tomas LOBGEOIS
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Return default {@code Role} (USER)
     *
     * @param defaultRole {@code true} to default role 'USER', {@code false} for
     *                    'ADMIN'
     * @return default USER {@code Role}
     */
    Role findByDefaultRole(boolean defaultRole);
}
