package com.biblioteca.repository;

import com.biblioteca.model.Prestito;
import com.biblioteca.model.Utente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrestitoRepository  extends AbstractRepository{

    public Prestito findById(int id) throws SQLException {
        String queryFindById = "SELECT isbn, titolo, id_prestito, nome, cognome, u.id_utente data_prestito FROM prestiti AS p JOIN  utenti AS u ON p.id_utente = u.id_utente WHERE " + id + " id_prestito " ;
//        SELECT id_prestito, nome, cognome, data_prestito FROM prestiti AS p JOIN  utenti AS u ON p.id_utente = u.id_utente WHERE  id_prestito = ?;
        PreparedStatement statement = connection.prepareStatement(queryFindById);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Prestito(resultSet.getInt("id"), resultSet.getString("cognome"),id );
        }
        System.out.println(resultSet.next());

        return null;
    }
}
