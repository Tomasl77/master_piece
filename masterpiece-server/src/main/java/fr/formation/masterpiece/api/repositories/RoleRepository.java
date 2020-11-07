package fr.formation.masterpiece.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.masterpiece.domain.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByDefaultRole(boolean defaultRole);
}
