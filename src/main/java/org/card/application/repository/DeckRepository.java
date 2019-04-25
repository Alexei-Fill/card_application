package org.card.application.repository;

import org.card.application.entity.Deck;
import org.card.application.entity.cardEnum.DeckType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeckRepository extends CrudRepository<Deck, Long> {

    @Query("select d from Deck d where lower(d.name) like lower(concat( '%', :searchTerm, '%')) and d.deckType = :deckType")
    List<Deck> findBySearchTerm(@Param("searchTerm") String searchTerm, @Param("deckType") DeckType deckType);
}
