package fr.formation.masterpiece.jobs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AccountLogIn extends AbstractEntity {

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public AccountLogIn(String username, String password) {
	this.username = username;
	this.password = password;
    }
}
