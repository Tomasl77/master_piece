package fr.formation.masterpiece.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * A representation of a {@code Category}.
 * <p>
 * Invariant is :
 * <ul>
 * <li>{@code String} name cannot be null
 * </ul>
 *
 * @author Tomas LOBGEOIS
 */
@Entity
@Table(name = "categories",
        uniqueConstraints = @UniqueConstraint(name = "UK_name",
                columnNames = "name"))
public class Category extends AbstractEntity {

    @Column(length = 20, nullable = false)
    private String name;

    protected Category() {
    }

    public String getName() {
	return name;
    }
}
