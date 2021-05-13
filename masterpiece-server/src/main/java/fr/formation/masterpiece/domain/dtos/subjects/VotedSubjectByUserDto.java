package fr.formation.masterpiece.domain.dtos.subjects;

import java.time.LocalDateTime;

/**
 * {@code DTO} representing an id of a {@code Subject} that a {@code CutsomUser}
 * has voted
 *
 * @author Tomas LOBGEOIS
 *
 */
public class VotedSubjectByUserDto {

    private Long id;

    private String title;

    private String description;

    private String category;

    private String requesterName;

    private LocalDateTime requestDate;

    /**
     * Empty no-args constructor
     */
    protected VotedSubjectByUserDto() {
    }

    public VotedSubjectByUserDto(Long id, String title, String description,
            String category, String requesterName, LocalDateTime requestDate) {
	this.id = id;
	this.title = title;
	this.description = description;
	this.category = category;
	this.requesterName = requesterName;
	this.requestDate = requestDate;
    }

    public Long getId() {
	return id;
    }

    public String getTitle() {
	return title;
    }

    public String getDescription() {
	return description;
    }

    public String getCategory() {
	return category;
    }

    public String getRequesterName() {
	return requesterName;
    }

    public LocalDateTime getRequestDate() {
	return requestDate;
    }
}
