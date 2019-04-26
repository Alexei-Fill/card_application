package org.card.application.service.impl;

import org.card.application.entity.Card;
import org.card.application.repository.CardRepository;
import org.card.application.service.BaseService;
import org.card.application.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CardServiceImpl implements BaseService<Card, Long>, CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card findById(Long id) {
        return cardRepository.getOne(id);
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card save(Card entity) {
        return cardRepository.save(entity);
    }

    @Override
    public Card update(Card entity) {
        return cardRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
