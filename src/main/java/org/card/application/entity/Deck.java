package org.card.application.entity;

import lombok.Data;
import org.card.application.entity.cardEnum.DeckType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "deck")
@Data
public class Deck implements Serializable {

    @Id
    @Column(name = "deck_id")
    @SequenceGenerator(name = "deck_seq", sequenceName = "deck_deck_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deck_seq")
    private long id;

    @Column(name = "deck_name")
    private String name;

    @OneToMany
    @JoinColumn(name = "card_deck_id")
    private List<Card> cards;
}
