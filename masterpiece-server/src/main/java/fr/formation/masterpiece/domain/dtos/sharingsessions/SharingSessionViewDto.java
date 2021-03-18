package fr.formation.masterpiece.domain.dtos.sharingsessions;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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

    private String subjectTitle;

    private String lecturerName;

    /**
     * Empty no-args constructor
     */
    protected SharingSessionViewDto() {
    }

    public SharingSessionViewDto(Long id, LocalDateTime startTime,
            LocalDateTime endTime, String subjectTitle, String lecturerName) {
	this.id = id;
	this.startTime = startTime;
	this.endTime = endTime;
	this.subjectTitle = subjectTitle;
	this.lecturerName = lecturerName;
    }

    public LocalDateTime getStartTime() {
	return startTime;
    }

    public LocalDateTime getEndTime() {
	return endTime;
    }

    public String getSubject() {
	return subjectTitle;
    }

    public String getLecturer() {
	return lecturerName;
    }
}
