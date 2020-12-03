package fr.formation.masterpiece.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;

@Entity
@Getter
@Table(name = "sharing_sessions",
        uniqueConstraints = @UniqueConstraint(name = "UK_starttime",
                columnNames = { "start_time" }))
public class SharingSession extends AbstractEntity {

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "subject_id", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_sharingsession_subject"))
    private Subject subject;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_sharingsession_userprofile"))
    private CustomUser lecturer;

    public void setSubject(Subject subject) {
	this.subject = subject;
    }

    public void setUser(CustomUser user) {
	this.lecturer = user;
    }
}
