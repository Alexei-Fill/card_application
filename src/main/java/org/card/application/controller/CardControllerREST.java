package org.card.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardControllerREST {

    @GetMapping("/hi")
    public String sayHello(){
        return "Hello everyone!!!!!!!";
    }

}
