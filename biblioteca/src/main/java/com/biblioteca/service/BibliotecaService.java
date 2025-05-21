package com.biblioteca.service;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.repository.BibliotecaRepository;

import java.sql.SQLException;
import java.util.List;

public class BibliotecaService {

    private static BibliotecaRepository bibliotecaRepository = new BibliotecaRepository();

    public void elencoLibri() {

        try {
            List<Libro> listaLibri = bibliotecaRepository.findAllLibri();

            for (Libro l : listaLibri) {
                System.out.println(l);
            }
        } catch (SQLException e) {
            System.err.println("Elenco non trovato " + e);
        }
    }
}
