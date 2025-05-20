package com.biblioteca.repository;

import com.biblioteca.main.Configuration;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteRepository {

    private static final Configuration c = new Configuration();
    private Connection connection;

    public UtenteRepository() {
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


    public List<Utente> findUtente() throws SQLException {

        List<Utente> utenti = new ArrayList<>();

        Statement statement = connection.createStatement();

        String queryFindUtente = "SELECT * FROM biblioteca.utenti";

        ResultSet resultSet = statement.executeQuery(queryFindUtente);

//        System.out.println("\n");
        while (resultSet.next()) {
            int idUtente = resultSet.getInt("id_utente");
            String nome = resultSet.getString("nome");
            String cognome = resultSet.getString("cognome");


            Utente utente1 = new Utente(nome,cognome,idUtente);
            utenti.add(utente1);
        }
        return utenti;
    }

    public Utente findById(int id) throws SQLException {
        String queryFindById = "SELECT * FROM biblioteca.utenti WHERE " + id + "= id_utente "; //specificare valori
        PreparedStatement statement = connection.prepareStatement(queryFindById);
        ResultSet resultSet = statement.executeQuery();
        System.out.println(resultSet.next());

        return new Utente(resultSet.getString("nome"), resultSet.getString("cognome"),id );

    }

    public Libro cercaTitolo (String titolo) throws SQLException {

        String queryCercaTitolo = "SELECT * FROM libri WHERE titolo LIKE CONCAT('%', ?, '%')";
        PreparedStatement statement = connection.prepareStatement(queryCercaTitolo);

        statement.setString(1, titolo);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String t = resultSet.getString("titolo");
            String a = resultSet.getString("autore");
            int annoP = resultSet.getInt("anno_pubblicazione");
            String i = resultSet.getString("isbn");
            return new Libro(t, a, annoP, i);
        } else {
            System.out.println("Nessun libro trovato.");
        }
        return null;
    }
}
