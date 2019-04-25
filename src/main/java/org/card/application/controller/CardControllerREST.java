package org.card.application.controller;

import org.card.application.repository.CardRepository;
import org.card.application.repository.DeckRepository;
import org.card.application.repository.UserRepository;
import org.card.application.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardControllerREST {

    @Autowired
    DeckRepository userRepository;

    @GetMapping("/hi")
    public String sayHello(){

        return userRepository.findAll().toString();
    }

}
