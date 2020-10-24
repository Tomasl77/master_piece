package fr.formation.masterpiece.domain.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class SharingSessionCreateDto {

    @NotNull
    private Long subjectId;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;
}
