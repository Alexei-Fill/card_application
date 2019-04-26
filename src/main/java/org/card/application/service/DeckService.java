package org.card.application.service;

import org.card.application.entity.Deck;

import java.util.List;

public interface DeckService {

    List<Deck> findBySearchTerm(String searchTerm);
}
