package org.card.application.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends Serializable, S extends Number> {

    T findById(S id);

    List<T> findAll();

    T saveOrUpdate(T entity);

    void delete (S id);
}
