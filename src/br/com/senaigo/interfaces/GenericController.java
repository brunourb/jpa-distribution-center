package br.com.senaigo.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 26/02/16.
 */
public interface GenericController<E> {

    public void create(final E entity);
    public E read(final E entity);
    public E update(final E entity);
    public void delete(final E entity);

    //-------------------------------------
    public List<E> create(final List<E> entities);
    public List<E> read(final Map<String, Object> params);
    public List<E> update(final List<E> entities);
    public List<E> delete(final List<E> entities);

    public boolean isValid(final Map<String, Object> params);

}
