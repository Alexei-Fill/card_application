package org.card.application.controller;

import org.card.application.entity.Deck;
import org.card.application.service.impl.DeckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ca/deck")
public class DeckControllerRest {

    @Autowired
    DeckServiceImpl deckService;

    @GetMapping
    public List<Deck> findAll(){
        return deckService.findAll();
    }

    @GetMapping("/{id}")
    public Deck findById(@PathVariable("id") long id){
        Deck deck = deckService.findById(id);
        System.out.println(deck);
        return deck;
    }

//    @GetMapping("/{searchTerm}")
//    public List<Deck> findBySearchTerm(@PathVariable("searchTerm") String searchTerm){
//        return deckService.findBySearchTerm(searchTerm);
//    }

    @PostMapping
    public Deck save (@RequestBody Deck deck){
        return deckService.saveOrUpdate(deck);
    }

    @PutMapping
    public Deck update (@RequestBody Deck deck){
        return deckService.saveOrUpdate(deck);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        deckService.delete(id);
    }
}
