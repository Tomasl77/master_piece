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
}
