package fr.formation.masterpiece.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.masterpiece.domain.dtos.views.MemberInfoViewDto;
import fr.formation.masterpiece.domain.entities.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<MemberInfoViewDto> getById(Long id);

    @Query("select id from Member m where m.user.id = :id")
    Long getMemberIdByUserId(Long id);

    List<MemberInfoViewDto> getAllProjectedBy();
}
