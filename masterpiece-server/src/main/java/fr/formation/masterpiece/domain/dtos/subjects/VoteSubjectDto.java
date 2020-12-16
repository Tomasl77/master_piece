package fr.formation.masterpiece.domain.dtos.subjects;

import lombok.Getter;

/**
 * {@code DTO} representing an id of a {@code Subject} that a {@code CutsomUser}
 * has voted
 *
 * @author Tomas LOBGEOIS
 *
 */
@Getter
public class VoteSubjectDto {

    private Long id;

    public VoteSubjectDto(Long id) {
	this.id = id;
    }
}
