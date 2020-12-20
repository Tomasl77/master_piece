package fr.formation.masterpiece.domain.dtos.subjects;

/**
 * {@code DTO} reprenting a {@code boolean} defining if a {@code CustomUser} has
 * voted to a {@code Subject}
 *
 * @author Tomas LOBGEOIS
 *
 */
public class SubjectVoteUpdateDto {

    private boolean hasVoted;

    public boolean isHasVoted() {
	return hasVoted;
    }
}
