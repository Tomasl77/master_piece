package fr.formation.masterpiece.domain.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.formation.masterpiece.commons.utils.BooleanConverter;
import lombok.Getter;

/**
 * Entity representing a {@code EntityUser}.
 * <p>
 * {@code EntityUser} is used to identify a user. It follows Spring
 * {@code UserDetails} most fields.
 * <p>
 * All fields are mandatory
 *
 * @author Tomas LOBGEOIS
 */
@Entity
@Getter
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "UK_username", columnNames = { "username" }),
        @UniqueConstraint(name = "UK_email", columnNames = { "email" }) })
public class EntityUser extends AbstractEntity {

    @Column(length = 255, nullable = false, updatable = false)
    private String username;

    @Column(length = 255, nullable = false, updatable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id",
                    foreignKey = @ForeignKey(name = "FK_user_role")),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    foreignKey = @ForeignKey(name = "FK_role_user")))
    private Set<Role> roles;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean enabled;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean accountNonExpired;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean accountNonLocked;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    /**
     * Empty no-args constructor
     */
    protected EntityUser() {
    }

    /**
     * Creates a new enabled custom user.
     *
     * @param password an encrypted password
     * @param username a unique username
     * @param roles    some roles
     */
    public EntityUser(String password, String username, Set<Role> roles) {
	this(password, username, roles, true);
    }

    /**
     * Creates a new custom user.
     *
     * @param password an encrypted password
     * @param username a unique username
     * @param roles    some roles
     * @param enabled  {@code true} if enabled; {@code false} otherwise
     */
    public EntityUser(String password, String username, Set<Role> roles,
            boolean enabled) {
	this.password = password;
	this.username = username;
	this.roles = roles;
	this.enabled = enabled;
    }

    /**
     * Creates a new custom user.
     *
     * @param password              an encrypted password
     * @param username              a unique username
     * @param roles                 some roles
     * @param enabled               {@code true} if enabled; {@code false}
     *                              otherwise
     * @param accountNonExpired     {@code true} if non expired; {@code false}
     *                              otherwise
     * @param accountNonLocked      {@code true} if non locked; {@code false}
     *                              otherwise
     * @param credentialsNonExpired {@code true} if non expired; {@code false}
     *                              otherwise
     * @param email                 a unique email
     */
    public EntityUser(String password, String username, Set<Role> roles,
            boolean enabled, boolean accountNonExpired,
            boolean accountNonLocked, boolean credentialsNonExpired,
            String email) {
	this.password = password;
	this.username = username;
	this.roles = roles;
	this.enabled = enabled;
	this.accountNonExpired = accountNonExpired;
	this.accountNonLocked = accountNonLocked;
	this.credentialsNonExpired = credentialsNonExpired;
	this.email = email;
    }

    @Override
    public String toString() {
	// password=[PROTECTED] for not displaying in logs
	return "{id: " + id + ", username: " + username
	        + ", password: [PROTECTED], enabled: " + enabled + ", email: "
	        + email + "}";
    }
}