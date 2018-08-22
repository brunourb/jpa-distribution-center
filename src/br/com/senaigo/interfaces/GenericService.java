/*
 * Copyright (c) 2016.
 * Exemplo prático das aulas de ORM/Android
 */

package br.com.senaigo.interfaces;

import br.com.senaigo.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 26/02/16.
 */
public interface GenericService<E> {

    public void create(final E entity) throws ServiceException;
    public E read(final E entity) throws ServiceException;
    public E update(final E entity) throws ServiceException;
    public void delete(final E entity) throws ServiceException;

    //-------------------------------------
    public List<E> create(final List<E> entities) throws ServiceException;
    public List<E> read(final Map<String, Object> params) throws ServiceException;
    public List<E> update(final List<E> entities) throws ServiceException;
    public List<E> delete(final List<E> entities) throws ServiceException;

    public boolean isValid(final Map<String, Object> params) throws ServiceException;

}
