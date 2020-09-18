package fr.formation.masterpiece.domain.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.formation.masterpiece.commons.utils.BooleanConverter;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_username", columnNames = { "username" }) })
public class CustomUser extends AbstractEntity {

    @Column(length = 255, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_user_userinfos"))
    private UserInfo info;

    protected CustomUser() {
	// Empty no-arg constructor (Hibernate)
    }

    /**
     * Creates a new enabled custom user.
     *
     * @param password an encrypted password
     * @param username a unique username
     * @param roles    some roles
     */
    public CustomUser(String password, String username, Set<Role> roles) {
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
    public CustomUser(String password, String username, Set<Role> roles,
            boolean enabled) {
	this.password = password;
	this.username = username;
	this.roles = roles;
	this.enabled = enabled;
    }

    public CustomUser(String password, String username, Set<Role> roles,
            boolean enabled, boolean accountNonExpired,
            boolean accountNonLocked, boolean credentialsNonExpired,
            UserInfo info) {
	this.password = password;
	this.username = username;
	this.roles = roles;
	this.enabled = enabled;
	this.accountNonExpired = accountNonExpired;
	this.accountNonLocked = accountNonLocked;
	this.credentialsNonExpired = credentialsNonExpired;
	this.info = info;
    }

    @Override
    public String toString() {
	// password=[PROTECTED] for not displaying in logs
	return "{id=" + id + ", username=" + username
	        + ", password=[PROTECTED], roles=" + roles + ", enabled="
	        + enabled + "infos = " + info + "}";
    }
}