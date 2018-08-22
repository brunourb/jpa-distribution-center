/*
 * Copyright (c) 2016.
 * Exemplo prático das aulas de ORM/Android
 */

package br.com.senaigo.exception;

/**
 * Created by bruno on 26/02/16.
 */
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}