package fr.formation.masterpiece.domain.dtos.subjects;

/**
 * {@code DTO} representing an id of a {@code Subject} that a {@code CutsomUser}
 * has voted
 *
 * @author Tomas LOBGEOIS
 *
 */
public class VoteSubjectDto {

    private Long id;

    /**
     * Empty no-args constructor
     */
    protected VoteSubjectDto() {
    }

    public VoteSubjectDto(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }
}
