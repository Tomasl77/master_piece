package fr.formation.masterpiece.api.repositories;

/**
 * Utility class providing Jpql Queries
 * <p>
 * Ease readibility of repositories and mutualize queries to facilitate their
 * management.
 *
 * @author Tomas LOBGEOIS
 *
 */
public final class JpqlQuery {

    private JpqlQuery() {
	// private constructor to ensure non-instanciability
    }

    /**
     * Update a {@code CustomUser} to set enabled to {@code false}
     *
     * @author Tomas LOBGEOIS
     */
    static final String DEACTIVATE_USER = "UPDATE CustomUser u SET u.enabled = false WHERE u.id = :userId";

    /**
     * Retrieve a {@code SharingSession} with both enable requester and
     * lecturer.
     *
     * @author Tomas LOBGEOIS
     */
    static final String SESSION_WITH_ENABLE_LECTURER = "SELECT s FROM SharingSession s "
            + "JOIN Subject su ON s.subject = su.id "
            + "JOIN CustomUser cu ON su.requester = cu.id "
            + "WHERE cu.enabled = true AND s.lecturer.enabled = true";

    /**
     * Update a {@code Subject} to set schedule to {@code true}
     *
     * @author Tomas LOBGEOIS
     */
    static final String SCHEDULE_SESSION = "UPDATE Subject s SET s.schedule = true WHERE s.id = :subjectId";

    /**
     * Create new {@code SubjectViewDtoWithVote} to have informations needed on
     * {@code Subject} and the join table with {@code CustomUser}
     */
    static final String SUBJECT_WITH_NUMBER_OF_VOTES = "SELECT new fr.formation.masterpiece.domain.dtos.subjects.SubjectViewDtoWithVote"
            + "(s.id, s.title, s.description, s.category.name, s.requester.username, count(v.id) as numberOfVote) "
            + "FROM Subject s LEFT JOIN s.voters v WHERE s.requester.enabled = true AND s.schedule = false GROUP BY s.id";

    /**
     * Create new {@code VoteSubjectDto} to retrieve informtation on which
     * subject the user has already voted
     */
    static final String USER_VOTED_SUBJECTS = "SELECT new fr.formation.masterpiece.domain.dtos.subjects.VoteSubjectDto"
            + "(s.id) FROM Subject s JOIN s.voters v WHERE v.id = :userId GROUP BY s.id";
}
