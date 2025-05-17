package com.biblioteca.repository;

import com.biblioteca.main.Configuration;
import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestito;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BibliotecaRepository {

    private static final Configuration c = new Configuration();
    private Connection connection;

    public BibliotecaRepository() {
        try {
            connection = DriverManager.getConnection(
                    c.getProperties().getProperty("jdbcurl"),
                    c.getProperties().getProperty("username"),
                    c.getProperties().getProperty("password")
            );
        } catch (SQLException e) {
            System.err.println("Errore di connessione " + e);
        }
    }


    public List<Libro> findAllLibri() throws SQLException {

        List<Libro> libri = new ArrayList<>();

        Statement statement = connection.createStatement();

        String queryFindAllLibri = "SELECT * FROM biblioteca.libri";

        ResultSet resultSet = statement.executeQuery(queryFindAllLibri);

//        System.out.println("\n");
        while (resultSet.next()) {
            String titolo = resultSet.getString("titolo");
            String autore = resultSet.getString("autore");
            int annoPubblicazione = resultSet.getInt("anno_pubblicazione");
            String isbn = resultSet.getString("isbn");

            Libro libro1 = new Libro(titolo, autore, annoPubblicazione, isbn);
            libri.add(libro1);
        }
        return libri;
    }

    public void addNewLibro(String titolo, String autore, int annoPubblicazione, String isbn) throws SQLException {
        String queryAddNewLibro = "INSERT INTO biblioteca.libri VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(queryAddNewLibro);

        statement.setString(1, titolo);
        statement.setString(2, autore);
        statement.setInt(3, annoPubblicazione);
        statement.setString(4, isbn);

        statement.executeUpdate();

    }


}
