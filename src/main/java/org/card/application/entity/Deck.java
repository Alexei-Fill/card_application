package org.card.application.entity;

import lombok.Data;
import org.card.application.entity.cardEnum.DeckType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "deck")
@SecondaryTable(name = "deck_type")
@Data
public class Deck {

    public Deck() {
    }

    public Deck(String name, DeckType deckType) {
        this.name = name;
        this.deckType = deckType;
    }

    @Id
    @Column(name = "deck_id")
    @SequenceGenerator(name = "deck_seq", sequenceName = "deck_deck_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deck_seq")
    private long id;

    @Column(name = "deck_name")
    private String name;

    @Column(table = "deck_type", name = "deck_type")
//    @OneToOne
//    @JoinTable(name = "deck_type", joinColumns = @JoinColumn(name = "deck_type"))
    @Enumerated(EnumType.STRING)
    private DeckType deckType;

    @OneToMany
    @JoinColumn(name = "card_deck_id")
    private List<Card> cards;
}