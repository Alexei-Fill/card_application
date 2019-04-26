package org.card.application.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_USER_ID_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private long id;

    @Column(name = "user_login")
    private String login;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_birthday")
    private LocalDate birthDay;

    @OneToMany
    @JoinColumn(name = "user_deck_id")
    private List<Deck> decks;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthDay=" + birthDay +
                ", decks=" + decks +
                '}';
    }
}
