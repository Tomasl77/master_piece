package fr.formation.masterpiece.domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class representing the identifier of an {@code Entity}.
 * <p>
 * Provides primary key to each {@code Entity} that extends it.
 *
 * @author Tomas LOBGEOIS
 */
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
