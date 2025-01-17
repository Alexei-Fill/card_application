package org.card.application.service.impl;

import org.card.application.entity.Deck;
import org.card.application.repository.DeckRepository;
import org.card.application.service.BaseService;
import org.card.application.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckServiceImpl implements BaseService<Deck, Long>, DeckService {

    @Autowired
    private DeckRepository deckRepository;

    @Override
    public Deck findById(Long id) {
        return deckRepository.getOne(id);
    }

    @Override
    public List<Deck> findAll() {
        return deckRepository.findAll();
    }

    @Override
    public Deck saveOrUpdate(Deck entity) {
        return deckRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        deckRepository.deleteById(id);
    }

    @Override
    public List<Deck> findBySearchTerm(String searchTerm) {
        return deckRepository.findBySearchTerm(searchTerm);
    }
}
