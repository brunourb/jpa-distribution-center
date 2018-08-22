/*
 * Copyright (c) 2016.
 * Exemplo prático das aulas de ORM/Android
 */

package br.com.senaigo.service.jdbc;

import br.com.senaigo.entities.Category;
import br.com.senaigo.exception.PersistenceException;
import br.com.senaigo.exception.ServiceException;
import br.com.senaigo.interfaces.GenericService;
import br.com.senaigo.persistence.jdbc.PCategoryJdbc;
import br.com.senaigo.util.DbUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 26/02/16.
 */
public class CategoryServiceJdbc implements GenericService<Category> {

    public final static Logger log = Logger.getLogger(PCategoryJdbc.class);

    PCategoryJdbc pCategoryJdbc = new PCategoryJdbc();

    public void create(Category entity) throws ServiceException {

        try {
            pCategoryJdbc.create(entity);
        } catch (PersistenceException e) {
           log.error(e.getMessage());
        }
        close();
    }

    @Override
    public Category read(Category entity) throws ServiceException {
        try {
            return pCategoryJdbc.read(entity);
        } catch (PersistenceException e) {
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }finally {
            close();
        }
    }

    @Override
    public Category update(Category entity) throws ServiceException {
        try {
           return pCategoryJdbc.update(entity);
        } catch (PersistenceException e) {
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void delete(Category entity) throws ServiceException {


    }

    @Override
    public List<Category> create(List<Category> entities) throws ServiceException {
        try {
            List<Category> categories = new ArrayList<Category>();

            for(Category category : entities){
                pCategoryJdbc.create(category);
                log.info(category.toString());
            }
            close();

        }catch (PersistenceException e){
            log.error(e.getMessage());
        }
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
        for(Category category : entities){
            delete(category);
        }
        return null;
    }

    @Override
    public boolean isValid(Map<String, Object> params) throws ServiceException {
        return false;
    }

    private void close(){
        DbUtil.close(pCategoryJdbc.getRs());
        DbUtil.close(pCategoryJdbc.getPreparedStatement());
        DbUtil.close(pCategoryJdbc.getConnection());
    }
}
