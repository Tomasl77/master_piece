package fr.formation.masterpiece.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.masterpiece.domain.dtos.views.UserProfileViewDto;
import fr.formation.masterpiece.domain.entities.UserProfile;

public interface UserProfileRepository
        extends JpaRepository<UserProfile, Long> {

    boolean existsByEmail(String email);

    List<UserProfileViewDto> getAllProjectedBy();

    Optional<UserProfileViewDto> getById(Long id);

    @Query("select id from UserProfile u where u.credentials.id = :id")
    Long getUserProfileIdByUserId(Long id);
}
