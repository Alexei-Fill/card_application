package org.card.application.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_token")
@Data
@AllArgsConstructor
public class UserToken implements Serializable {
    @Id
    @Column(name = "user_token_id")
    @SequenceGenerator(name = "user_token_seq", sequenceName = "user_token_user_token_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_token_seq")
    private long id;

    @Column(name = "user_token")
    private String token;

    @Column(name = "user_token_expiration_time")
    private LocalDateTime tokenExpirationTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
