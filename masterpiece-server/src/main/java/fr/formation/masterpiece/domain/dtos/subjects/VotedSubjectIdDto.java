package fr.formation.masterpiece.domain.dtos.subjects;

/**
 * {@code DTO} representing an id of a {@code Subject} that a {@code CutsomUser}
 * has voted
 *
 * @author Tomas LOBGEOIS
 *
 */
public class VotedSubjectIdDto {

    private Long id;

    /**
     * Empty no-args constructor
     */
    protected VotedSubjectIdDto() {
    }

    public VotedSubjectIdDto(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }
}
