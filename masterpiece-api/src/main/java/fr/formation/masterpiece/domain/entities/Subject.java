package fr.formation.masterpiece.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_subject_user"))
    private UserAuth user;
}
