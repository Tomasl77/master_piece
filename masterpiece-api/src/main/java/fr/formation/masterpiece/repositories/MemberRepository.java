package fr.formation.masterpiece.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.masterpiece.domain.dtos.views.MemberInfoViewDto;
import fr.formation.masterpiece.domain.entities.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<MemberInfoViewDto> getById(Long id);
}
