package com.biblioteca.repository;

import com.biblioteca.main.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractRepository {

    private Configuration c = new Configuration();
    protected Connection connection;

    public AbstractRepository(){
        try {
            connection = DriverManager.getConnection(
                    c.getProperties().getProperty("jdbcurl"),
                    c.getProperties().getProperty("username"),
                    c.getProperties().getProperty("password")
            );
        } catch (SQLException e) {
            throw new IllegalStateException("Errore di connessione ", e);
        }
    }

    public void close(){

        try {
            connection.close();

        }catch (SQLException e){
            System.err.println("Errore nella chiusura della connessione");
        }

    }

}
