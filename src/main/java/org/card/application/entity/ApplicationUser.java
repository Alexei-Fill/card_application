package org.card.application.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.card.application.entity.cardEnum.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class ApplicationUser implements Serializable {

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_USER_ID_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private long id;

    @Column(name = "user_login")
    private String login;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_birthday")
    private LocalDate birthDay;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_deck_id")
    private List<Deck> decks;
}
