package com.biblioteca.repository;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestito;
import com.biblioteca.model.Utente;

import java.sql.*;
import java.time.LocalDateTime;

public class PrestitoRepository  extends AbstractRepository{
 //inserire aggiungi prestito    ed        findBYUtente


    private void update(Prestito prestito) throws  SQLException {
        String querryUpDate ="UPDATE prestiti " +
                " SET data_restituzione = ?" +
                " where id_prestito= ?";
        PreparedStatement statement = connection.prepareStatement(querryUpDate);

        Timestamp dataRestituzione= prestito.getDataRestituzione()== null ? null :Timestamp.valueOf(prestito.getDataRestituzione());
        statement.setTimestamp(1, dataRestituzione); // LocalDateTime -> Timestamp
        statement.setInt(2, prestito.getIdPrestito());
        int rowsAffected = statement.executeUpdate();
    }

    private void create(Prestito prestito) throws  SQLException {
        String queryCreate ="INSERT INTO prestiti (id_utente, isbn, data_prestito)" +
                " VALUES(?,?,?)" ;
        PreparedStatement statement = connection.prepareStatement(queryCreate);

        statement.setInt(1, prestito.getUtente().getIdUtente());
        statement.setString(2, prestito.getLibro().getISBN());
        statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

        int rowsAffected = statement.executeUpdate();
    }
    public void save(Prestito prestito) throws SQLException{
        if (prestito.getIdPrestito() == null){
            create(prestito);
        } else {
            update(prestito);
        }
    }

    public Prestito findById(int id) throws SQLException {

        String queryFindById =
                "SELECT * " +
                "FROM prestiti AS p JOIN  utenti AS u ON p.id_utente = u.id_utente " +
                "JOIN libri AS l ON l.isbn = p.isbn WHERE p.id_prestito = ?";

        PreparedStatement statement = connection.prepareStatement(queryFindById);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Utente u = new Utente(resultSet.getString("nome"),
                                  resultSet.getString("cognome"),
                                    resultSet.getInt("id_utente"));

            Libro l = new Libro(resultSet.getString("titolo"),
                                resultSet.getString("autore"),
                                resultSet.getInt("anno_pubblicazione"),
                                resultSet.getString("isbn"));

            Timestamp data_prestito = resultSet.getTimestamp("data_prestito");
            Timestamp data_restituzione = resultSet.getTimestamp("data_restituzione");
            return new Prestito(l,u,data_prestito.toLocalDateTime(),
                    data_restituzione == null ? null : data_restituzione.toLocalDateTime());
        }


        return null;
    }



}
