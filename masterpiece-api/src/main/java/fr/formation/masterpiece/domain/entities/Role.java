package fr.formation.masterpiece.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends AbstractEntity {

    @NotBlank
    @Column(name = "code", nullable = false, unique = true)
    String code;

    @NotNull
    @Column(nullable = false)
    boolean defaultRole;
}
