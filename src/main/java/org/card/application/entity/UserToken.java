package org.card.application.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_token")
@Data
public class UserToken implements Serializable {
    @Id
    @Column(name = "user_token_id")
    @SequenceGenerator(name = "user_token_seq", sequenceName = "user_token_user_token_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_token_seq")
    private long id;

    @Column(name = "user_token")
    private String token;

    @Column(name = "user_token_expiration_time")
    private LocalDate tokenExpirationTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
