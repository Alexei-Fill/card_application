package org.card.application.controller;

import org.card.application.entity.Card;
import org.card.application.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ca/card")
public class CardControllerRest {

    @Autowired
    CardServiceImpl cardService;

    @GetMapping
    public List<Card> findAll(){
        return cardService.findAll();
    }

    @GetMapping("/{id}")
    public Card findById(@PathVariable("id") long id){
        return cardService.findById(id);
    }

    @PostMapping
    public Card save (@RequestBody Card card){
        return cardService.save(card);
    }

    @PutMapping
    public Card update (@RequestBody Card card){
        return cardService.saveOrUpdate(card);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        cardService.delete(id);
    }
}