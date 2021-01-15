package fr.formation.masterpiece.domain.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.formation.masterpiece.commons.utils.BooleanConverter;

/**
 * Entity representing a {@code Role}.
 * <p>
 * Role define {@code CustomUser} authorizations.
 *
 * @author Tomas LOBGEOIS
 *
 */
@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_code", columnNames = { "code" }) })
public class Role extends AbstractEntity {

    @Column(name = "code", nullable = false)
    String code;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    boolean defaultRole;

    public Role(String code) {
	this.code = code;
    }

    public String getCode() {
	return code;
    }

    protected Role() {
    }
}
