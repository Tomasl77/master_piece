package fr.formation.masterpiece.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.masterpiece.domain.entities.UserProfile;

public interface UserProfileRepository
        extends JpaRepository<UserProfile, Long> {

    boolean existsByEmail(String email);

    @Override
    List<UserProfile> findAll();

    Optional<UserProfile> getById(Long id);

    @Query("SELECT u FROM UserProfile u WHERE u.credentials.id = :id")
    Optional<UserProfile> findProfileWithUserCredentialsId(
            @Param("id") Long id);
}
