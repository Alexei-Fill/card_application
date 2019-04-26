package org.card.application.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "card")
@Data
public class Card implements Serializable {

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


}
