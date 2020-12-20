package fr.formation.masterpiece.domain.dtos.sharingsessions;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;

/**
 * {@code DTO} representation of a {@code SharingSession}.
 * <p>
 * This DTO give all informations about a {@code SharingSession}.
 *
 * @author Tomas LOBGEOIS
 */
public class SharingSessionViewDto {

    private Long id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private SubjectViewDto subject;

    private CustomUserViewDto lecturer;

    /**
     * Empty no-args constructor
     */
    protected SharingSessionViewDto() {
	//
    }

    public LocalDateTime getStartTime() {
	return startTime;
    }

    public LocalDateTime getEndTime() {
	return endTime;
    }

    public SubjectViewDto getSubject() {
	return subject;
    }

    public CustomUserViewDto getLecturer() {
	return lecturer;
    }
}
