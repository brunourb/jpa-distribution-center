/*
 * Copyright (c) 2016.
 * Exemplo prático das aulas de ORM/Android
 */

package br.com.senaigo.controller;

import br.com.senaigo.entities.Category;
import br.com.senaigo.exception.ServiceException;
import br.com.senaigo.interfaces.GenericController;
import br.com.senaigo.service.jdbc.CategoryServiceJdbc;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 26/02/16.
 */
public class CategoryController implements GenericController<Category> {

    public final static Logger log = Logger.getLogger(CategoryController.class);

    CategoryServiceJdbc categoryService = new CategoryServiceJdbc();


    @Override
    public void create(Category entity) {
        try {
            categoryService.create(entity);
        }catch (ServiceException e){
            log.error(e.getMessage());
        }
    }

    @Override
    public Category read(Category entity) {
        try {
            return categoryService.read(entity);
        }catch(ServiceException e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Category update(Category entity) {
        try {
            categoryService.update(entity);
        }catch(ServiceException e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Category entity) {
        try {
            categoryService.delete(entity);
        }catch(ServiceException e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Category> create(List<Category> categories) {
        try {
            categoryService.create(categories);
        } catch(ServiceException e){
            log.error(e.getMessage());

        }
        return null;
    }

    @Override
    public List<Category> read(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<Category> update(List<Category> entities) {
        return null;
    }

    @Override
    public List<Category> delete(List<Category> entities) {
        return null;
    }

    @Override
    public boolean isValid(Map<String, Object> params) {
        return false;
    }
}
