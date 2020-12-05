package fr.formation.masterpiece.domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;

/**
 * Abstract class representing the identifier of an {@code Entity}.
 * <p>
 * Provides primary key to each {@code Entity} that extends it.
 *
 * @author Tomas LOBGEOIS
 */
@MappedSuperclass
@Getter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
