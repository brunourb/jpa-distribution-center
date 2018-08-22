package br.com.senaigo.service.jpa;

import br.com.senaigo.entities.Category;
import br.com.senaigo.exception.ServiceException;
import br.com.senaigo.interfaces.GenericService;

import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 22/04/16.
 */
public class CategoryServiceJpa implements GenericService<Category> {

    @Override
    public void create(Category entity) throws ServiceException {

    }

    @Override
    public Category read(Category entity) throws ServiceException {
        return null;
    }

    @Override
    public Category update(Category entity) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Category entity) throws ServiceException {

    }

    @Override
    public List<Category> create(List<Category> entities) throws ServiceException {
        return null;
    }

    @Override
    public List<Category> read(Map<String, Object> params) throws ServiceException {
        return null;
    }

    @Override
    public List<Category> update(List<Category> entities) throws ServiceException {
        return null;
    }

    @Override
    public List<Category> delete(List<Category> entities) throws ServiceException {
        return null;
    }

    @Override
    public boolean isValid(Map<String, Object> params) throws ServiceException {
        return false;
    }
}
