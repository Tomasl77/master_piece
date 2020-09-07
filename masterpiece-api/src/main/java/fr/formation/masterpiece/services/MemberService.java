package fr.formation.masterpiece.services;

import fr.formation.masterpiece.domain.dtos.MemberCreateDto;
import fr.formation.masterpiece.domain.dtos.MemberDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.dtos.views.MemberInfoViewDto;

public interface MemberService {

    boolean isValid(String username);

    MemberDto create(MemberCreateDto dto);

    MemberInfoViewDto getOne(Long id);

    UsernameCheckDto checkUsername(String username);

    void deleteOne(Long id);
}
