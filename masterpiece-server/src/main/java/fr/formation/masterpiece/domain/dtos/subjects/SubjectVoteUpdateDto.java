package fr.formation.masterpiece.domain.dtos.subjects;

import lombok.Getter;

/**
 * {@code DTO} reprenting a {@code boolean} defining if a {@code CustomUser} has
 * voted to a {@code Subject}
 *
 * @author Tomas LOBGEOIS
 *
 */
@Getter
public class SubjectVoteUpdateDto {

    private boolean hasVoted;
}
