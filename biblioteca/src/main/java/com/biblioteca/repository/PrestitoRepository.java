package com.biblioteca.repository;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestito;
import com.biblioteca.model.Utente;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PrestitoRepository  extends AbstractRepository{
 //inserire aggiungi prestito    ed        findBYUtente


    public void update(Prestito prestito) throws  SQLException {
        String querryUpDate ="UPDATE prestiti " +
                " SET data_restituzione = ?" +
                " where id_prestito= ?";
        PreparedStatement statement = connection.prepareStatement(querryUpDate);
//        statement.setTimestamp(1, prestito.getDataRestituzione());
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

            Date data_prestito = resultSet.getDate("data_prestito");
            Date data_restituzione = resultSet.getDate("data_restituzione");
            return new Prestito(l,u,data_prestito,data_restituzione);
        }


        return null;
    }
}
