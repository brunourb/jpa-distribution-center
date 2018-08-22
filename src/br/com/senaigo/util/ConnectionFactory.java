/*
 * Copyright (c) 2016.
 * Exemplo prático das aulas de ORM/Android
 */

package br.com.senaigo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * {@link http://theopentutorials.com/tutorials/java/jdbc/jdbc-examples-introduction/}
 * @author bruno
 *
 */
public class ConnectionFactory {
	
	 //static reference to itself
    private static ConnectionFactory instance = new ConnectionFactory();
    public static final String URL = "jdbc:mysql://192.168.56.102:3306/northwind";
	public final static String USER = "root";
	public final static String PASSWORD = "123456";
     
    //private constructor
    private ConnectionFactory() {
        try {
            Class.forName(EnumDrivers.MYSQL_DRIVER_CLASS.getValor());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }   
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
}
