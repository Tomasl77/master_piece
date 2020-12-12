package fr.formation.masterpiece.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.formation.masterpiece.commons.utils.BooleanConverter;
import lombok.Getter;

/**
 * Entity representing a {@code SharingSession}.
 * <p>
 * Invariants are :
 * <ul>
 * <li>title cannot be null, and length max. 30 chars</li>
 * <li>description cannot be null</li>
 * <li>requestDate cannot be null</li>
 * <li>{@code Category} must exists in database</li>
 * <li>{@code CustomUser} must exists in database</li>
 * </ul>
 * All fields are mandatory, except schedule. Must be false at creation
 *
 * @author Tomas LOBGEOIS
 *
 */
@Entity
@Getter
@Table(name = "subjects",
        uniqueConstraints = @UniqueConstraint(
                name = "UK_requestdate_requesterid",
                columnNames = { "request_date", "requester_id" }))
public class Subject extends AbstractEntity {

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate;

    @Column(name = "schedule", nullable = false)
    @Convert(converter = BooleanConverter.class)
    private boolean schedule;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "requester_id", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_subject_userprofile"))
    private CustomUser requester;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_vote_subject",
            joinColumns = @JoinColumn(name = "subject_id",
                    foreignKey = @ForeignKey(name = "FK_subject_has_votes")),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    foreignKey = @ForeignKey(name = "FK_user_has_voted")))
    private List<CustomUser> voters;

    public void setUser(CustomUser user) {
	this.requester = user;
    }

    @Override
    public String toString() {
	return "{title: " + title + ", description: " + description
	        + ", category: " + category + ", requester: " + requester + "}";
    }
}
