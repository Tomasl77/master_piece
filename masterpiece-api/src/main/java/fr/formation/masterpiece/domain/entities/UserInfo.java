package fr.formation.masterpiece.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_info", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_email", columnNames = { "email" }) })
public class UserInfo extends AbstractEntity {

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    public UserInfo() {
    }

    public UserInfo(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return "{email =" + email + "}";
    }
}
