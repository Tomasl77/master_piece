package fr.formation.masterpiece.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;

@Entity
@Getter
@Table(name = "categories",
        uniqueConstraints = @UniqueConstraint(name = "UK_name",
                columnNames = "name"))
public class Category {

    @Id
    private Long id;

    private String name;
}
