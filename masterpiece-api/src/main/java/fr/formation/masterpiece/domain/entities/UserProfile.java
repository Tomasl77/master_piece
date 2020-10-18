package fr.formation.masterpiece.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_profiles", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_email", columnNames = { "email" }) })
public class UserProfile extends AbstractEntity {

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_credentials_id", nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_usercredentials_user"))
    private UserCredentials credentials;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Subject> subjects;

    public UserProfile() {
    }

    public UserProfile(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return "{email =" + email + "credentials = " + credentials + "}";
    }
}
