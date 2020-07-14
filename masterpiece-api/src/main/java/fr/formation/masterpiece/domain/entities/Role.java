package fr.formation.masterpiece.domain.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import fr.formation.masterpiece.commons.utils.BooleanConverter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends AbstractEntity {

    @Column(name = "code", nullable = false, unique = true)
    String code;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    boolean defaultRole;

    public Role(String code) {
	this.code = code;
    }

    public Role() {
    }
}
