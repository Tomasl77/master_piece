package fr.formation.masterpiece.domain.dtos.sharingsessions;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import fr.formation.masterpiece.commons.annotations.NotSameDay;
import lombok.Getter;

@Getter
public class SharingSessionCreateDto {

    @NotNull
    private Long subjectId;

    @NotSameDay
    @Future
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @Future
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    /**
     * Empty no-args constructor
     */
    protected SharingSessionCreateDto() {
    }
}
