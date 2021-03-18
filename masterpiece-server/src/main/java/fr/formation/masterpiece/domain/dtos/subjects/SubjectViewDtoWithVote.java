package fr.formation.masterpiece.domain.dtos.subjects;

/**
 * {@code DTO} representation of a {@code Subject}.
 * <p>
 * This DTO give all informations about a {@code Subject}.
 *
 * @author Tomas LOBGEOIS
 */
public class SubjectViewDtoWithVote {

    private Long id;

    private String title;

    private String description;

    private String categoryName;

    private String requesterUsername;

    private boolean hasVoted;

    private Long numberOfVote;

    public SubjectViewDtoWithVote(Long id, String title, String description,
            String category, String requester, Long numberOfVote) {
	this.id = id;
	this.title = title;
	this.description = description;
	this.categoryName = category;
	this.requesterUsername = requester;
	this.numberOfVote = numberOfVote;
    }

    /**
     * Empty no-args constructor
     */
    protected SubjectViewDtoWithVote() {
    }

    public void setHasVoted(boolean hasVoted) {
	this.hasVoted = hasVoted;
    }

    public Long getId() {
	return id;
    }
}
