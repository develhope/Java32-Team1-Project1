package com.biblioteca.repository;

import com.biblioteca.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LibroRepository extends AbstractRepository {


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

    public void addNewLibro(Libro l) throws SQLException {
        String queryAddNewLibro = "INSERT INTO biblioteca.libri (titolo , autore , anno_pubblicazione , isbn)" +
                " VALUES (?, ?, ?, ?)"; //specificare valori
        PreparedStatement statement = connection.prepareStatement(queryAddNewLibro);

        statement.setString(1,l.getTitolo() );
        statement.setString(2, l.getAutore());
        statement.setInt(3, l.getAnnoPubblicazione());
        statement.setString(4, l.getISBN());

        statement.executeUpdate();

    }

    public Libro cercaTitolo(String titolo) throws SQLException {

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

    //metodo ricerca libro per isbn
    public Libro findById(String isbn){
        String query = "SELECT * FROM libri WHERE isbn = ?";
        //try with resources per chiudere le risorse al termine del blocco try-catch
        try (PreparedStatement preparedStatement = connection.prepareStatement(query))
        {

            preparedStatement.setString(1,isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new Libro(resultSet.getString("titolo"),
                                 resultSet.getString("autore"),
                                 resultSet.getInt("anno_pubblicazione"),
                                 isbn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}
