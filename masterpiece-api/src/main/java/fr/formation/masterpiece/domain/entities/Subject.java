package fr.formation.masterpiece.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "category", nullable = false,
            columnDefinition = "ENUM('FRONTEND', 'BACKEND', 'DATABASE', 'RIFT', 'OTHER')")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "total_vote", nullable = false)
    private int vote = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_subject_user"))
    private CustomUser user;
}