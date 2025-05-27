package com.biblioteca.service;

import com.biblioteca.model.Prestito;
import com.biblioteca.repository.PrestitoRepository;

import java.sql.SQLException;

public class PrestitoService {

//    private PrestitoRepository prestitoRepository = new PrestitoRepository();
//
//    public boolean create(Prestito p) throws SQLException {
//        int numeroCopie = prestitoRepository.getNumeroCopieDisponibili(p.getLibro());
//
//        if (numeroCopie > 0) {
//            prestitoRepository.save(p);
//            return true;
//        } else {
//            return false;
//        }
//    }

}
    private PrestitoRepository prestitoRepository = new PrestitoRepository();

    public boolean create(Prestito p) throws SQLException {
        int numeroCopie = prestitoRepository.getNumeroCopieDisponibili(p.getLibro());

        if (numeroCopie > 0) {
            prestitoRepository.save(p);
            return true;
        } else {
            return false;
        }
    }
