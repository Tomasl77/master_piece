package fr.formation.masterpiece.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vote extends AbstractEntity {

    @Column(name = "total_vote", nullable = false)
    private int vote;
}
