package fr.formation.masterpiece.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "subject",
        indexes = { @Index(name = "IDX_subject_topic", columnList = "id") })
public class Subject extends AbstractEntity {

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "total_vote", nullable = false)
    private int vote;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "requester_id", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_subject_userprofile"))
    private UserProfile user;
}
