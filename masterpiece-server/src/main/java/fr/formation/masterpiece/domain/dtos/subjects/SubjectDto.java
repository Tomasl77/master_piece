package fr.formation.masterpiece.domain.dtos.subjects;

import lombok.Getter;

/**
 * {@code DTO} representation of the title of a {@code Subject}
 *
 * @author Tomas LOBGEOIS
 *
 */
@Getter
public class SubjectDto {

    private String title;

    /**
     * Empty no-args constructor
     */
    protected SubjectDto() {
	//
    }
}
