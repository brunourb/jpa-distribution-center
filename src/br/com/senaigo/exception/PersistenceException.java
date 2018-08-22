/*
 * Copyright (c) 2016.
 * Exemplo prático das aulas de ORM/Android
 */

package br.com.senaigo.exception;

/**
 * Created by bruno on 26/02/16.
 */
public class PersistenceException extends Exception {

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}