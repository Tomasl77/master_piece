package fr.formation.masterpiece.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDto {

    private Long id;

    private String username;

    public CustomUserDto() {
    }
}