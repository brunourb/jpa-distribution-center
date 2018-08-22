/*
 * Copyright (c) 2016.
 * Exemplo prático das aulas de ORM/Android
 */

package br.com.senaigo.interfaces;

import br.com.senaigo.exception.PersistenceException;
import br.com.senaigo.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 26/02/16.
 */
public interface GenericDao<E> {

    public void create(final E entity) throws PersistenceException;
    public E read(final E entity) throws PersistenceException;
    public E update(final E entity) throws PersistenceException;
    public void delete(final E entity) throws PersistenceException;

    //-------------------------------------
    public void create(final List<E> entities) throws PersistenceException;
    public List<E> read(final Map<String, Object> params) throws PersistenceException, SQLException;
    public List<E> update(final List<E> entities) throws PersistenceException;
    public void delete(final List<E> entities) throws PersistenceException;

    public boolean isValid(final Map<String, Object> params) throws PersistenceException;

}
