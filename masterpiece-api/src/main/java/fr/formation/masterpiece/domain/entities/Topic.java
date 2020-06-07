package fr.formation.masterpiece.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tomas
 *
 *         Topic entity. Takes id from superclass
 *
 */
@Entity
@Getter
@Setter
public class Topic extends AbstractEntity {

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "requester", nullable = false)
    private String requester;

    @Column(name = "request_date", nullable = false)
    private Date requestDate;

    /**
     * Empty constructor
     */
    public Topic() {
	// TODO Auto-generated constructor stub
    }
}