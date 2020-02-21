package fr.formation.masterpiece.domain.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @ManyToMany
    @Column(nullable = false)
    private Set<Role> role;

    @Column(nullable = false)
    private boolean enabled = true;

    public User() {
    }
}
