/*
 * Copyright (c) 2016.
 * Exemplo prático das aulas de ORM/Android
 */

package br.com.senaigo.util;

/**
 * Created by bruno on 26/02/16.
 */
public enum EnumDrivers {

    MYSQL_DRIVER_CLASS("com.mysql.jdbc.Driver"),
    POSTGRES_DRIVER_CLASS("org.postgresql.Driver"),
    ORACLE_DRIVER_CLASS("oracle.jdbc.driver.OracleDriver"),
    MARIA_DB_DRIVER_CLASS("org.mariadb.jdbc.Driver"),
    MS_DRIVER_CLASS("com.microsoft.sqlserver.jdbc.SQLServerD\u200C\u200Briver");


    public String valor;

    /**
     * Construtor padrão do enumerador que recebe o valor a ser atribuído.
     *
     * @param valor valor do enumerador.
     */
    private EnumDrivers(final String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}