package fr.formation.masterpiece.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_email", columnNames = { "email" }) })
public class Member extends AbstractEntity {

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_member_user"))
    private UserAuth user;

    public Member() {
    }

    public Member(String email, UserAuth user) {
	this.email = email;
	this.user = user;
    }
}
