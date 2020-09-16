package fr.formation.masterpiece.domain.dtos;

import lombok.Getter;

@Getter
public class MemberDto {

    private String email;

    private UserDto user;

    public MemberDto() {
    }
}