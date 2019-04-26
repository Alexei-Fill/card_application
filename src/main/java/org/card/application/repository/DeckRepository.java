package org.card.application.repository;

import org.card.application.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck, Long> {

    @Query("select d from Deck d where lower(d.name) like lower(concat( '%', :searchTerm, '%'))")
    List<Deck> findBySearchTerm(@Param("searchTerm") String searchTerm);
}
