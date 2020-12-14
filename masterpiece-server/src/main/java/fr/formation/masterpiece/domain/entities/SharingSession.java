package fr.formation.masterpiece.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;

/**
 * Entity representing a {@code SharingSession}.
 * <p>
 * Invariants are :
 * <ul>
 * <li>startTime cannot be null</li>
 * <li>endTime cannot be null</li>
 * <li>{@code Subject} must exists in database</li>
 * <li>{@code CustomUser} must exists in database</li>
 * </ul>
 *
 * @author Tomas LOBGEOIS
 *
 */
@Entity
@Getter
@Table(name = "sharing_sessions",
        uniqueConstraints = @UniqueConstraint(name = "UK_starttime",
                columnNames = { "start_time" }))
public class SharingSession extends AbstractEntity {

    @Column(name = "start_time", nullable = false, updatable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false, updatable = false)
    private LocalDateTime endTime;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "subject_id", referencedColumnName = "id",
            nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "FK_sharingsession_subject"))
    private Subject subject;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_sharingsession_user"))
    private CustomUser lecturer;

    public void setSubject(Subject subject) {
	this.subject = subject;
    }

    public void setUser(CustomUser user) {
	this.lecturer = user;
    }
}
