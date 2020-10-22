package fr.formation.masterpiece.domain.dtos;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class SharingSessionCreateDto {

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
