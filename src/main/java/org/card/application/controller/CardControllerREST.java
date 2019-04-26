package org.card.application.controller;

import org.card.application.entity.User;
import org.card.application.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardControllerREST {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/hi")
    public Iterable<User> sayHello(){
        return userService.findAll();
    }

}
