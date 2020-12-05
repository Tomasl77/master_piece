package fr.formation.masterpiece.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;

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
@Getter
@Table(name = "categories",
        uniqueConstraints = @UniqueConstraint(name = "UK_name",
                columnNames = "name"))
public class Category {

    @Id
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;
}
