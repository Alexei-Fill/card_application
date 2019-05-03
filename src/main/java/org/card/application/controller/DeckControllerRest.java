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
        return deckService.findById(id);
    }

    @GetMapping("/{searchTerm}")
    public List<Deck> findBySearchTerm(@PathVariable("searchTerm") String searchTerm){
        return deckService.findBySearchTerm(searchTerm);
    }

    @PostMapping
    public Deck save (@RequestBody Deck deck){
        return deckService.save(deck);
    }

    @PutMapping
    public Deck update (@RequestBody Deck deck){
        return deckService.update(deck);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        deckService.delete(id);
    }
}
