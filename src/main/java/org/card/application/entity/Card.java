package org.card.application.entity;

import lombok.Data;
import lombok.ToString;
import org.card.application.entity.cardEnum.CardStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "card")
@SecondaryTable(name = "card_status")
@Data
public class Card {

    @Id
    @Column(name = "card_id")
    @SequenceGenerator(name = "card_seq", sequenceName = "card_card_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq")
    private long id;

    @Column(name = "card_front_side")
    private String frontSide;

    @Column(name = "card_back_side")
    private String backSide;

    @Column(name = "card_front_voice")
    private String frontVoice;

    @Column(name = "card_back_voice")
    private String backVoice;

    @Column(name = "card_status", table = "card_status")
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;


//    @ManyToMany
//    @JoinTable(name = "card_status",
//            joinColumns = @JoinColumn(name = "card_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    @ToString.Exclude
//    private List<User> users;
}
