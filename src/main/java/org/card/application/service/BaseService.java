package org.card.application.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends Serializable, S extends Number> {

    T findById(S id);

    List<T> findAll();

    T save (T entity);

    T update (T entity);

    void delete (S id);
}
