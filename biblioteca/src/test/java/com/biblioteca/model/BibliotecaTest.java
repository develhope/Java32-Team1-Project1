package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {

    @BeforeEach
    public void setUp() {
        // Resetta lo stato della biblioteca prima di ogni test
        Biblioteca.size = 0;
        for (int i = 0; i < Biblioteca.dati.length; i++) {
            Biblioteca.dati[i] = null;
        }
    }

    @Test
    public void testAggiungiLibro() {
        // Arrange
        Libro libro = new Libro("Il Nome della Rosa", "Umberto Eco", 1980, 9788804665298L);

        // Act
        Biblioteca.aggiungi(libro);

        // Assert
        assertEquals(1, new Biblioteca().dimensione(), "La dimensione dovrebbe essere 1 dopo aver aggiunto un libro");
        assertEquals(libro, new Biblioteca().get(0), "Il libro recuperato dovrebbe essere lo stesso aggiunto");
    }

    @Test
    public void testAggiungiLibroOltreCapacita() {
        // Arrange: Riempi l'array oltre la capacità (100)
        for (int i = 0; i < 100; i++) {
            Biblioteca.aggiungi(new Libro("Titolo" + i, "Autore", 2023, 1234567890000L + i));
        }
        int dimensioneIniziale = new Biblioteca().dimensione();
        Libro libroExtra = new Libro("Extra", "Autore", 2023, 9999999999999L);

        // Act
        Biblioteca.aggiungi(libroExtra);

        // Assert
        assertEquals(dimensioneIniziale, new Biblioteca().dimensione(), "La dimensione non dovrebbe aumentare oltre 100");
    }

    @Test
    public void testRimuoviLibro() {
        // Arrange
        Libro libro1 = new Libro("1984", "George Orwell", 1949, 9780451524935L);
        Libro libro2 = new Libro("Il Nome della Rosa", "Umberto Eco", 1980, 9788804665298L);
        Biblioteca.aggiungi(libro1);
        Biblioteca.aggiungi(libro2);

        // Act
        Biblioteca.rimuovi(0);

        // Assert
        assertEquals(1, new Biblioteca().dimensione(), "La dimensione dovrebbe essere 1 dopo la rimozione");
        assertEquals(libro2, new Biblioteca().get(0), "Il libro rimanente dovrebbe essere il secondo aggiunto");
    }

    @Test
    public void testRimuoviDaBibliotecaVuota() {
        // Act
        Biblioteca.rimuovi(0);

        // Assert
        assertEquals(0, new Biblioteca().dimensione(), "La dimensione dovrebbe rimanere 0 se la biblioteca è vuota");
    }

    @Test
    public void testGetLibro() {
        // Arrange
        Libro libro = new Libro("1984", "George Orwell", 1949, 9780451524935L);
        Biblioteca.aggiungi(libro);

        // Act
        Libro recuperato = new Biblioteca().get(0);

        // Assert
        assertEquals(libro, recuperato, "Il libro recuperato dovrebbe essere uguale a quello aggiunto");
    }

    @Test
    public void testElencoLibriVuoto() {
        // Act & Assert
        // Non possiamo testare direttamente l'output di System.err, ma possiamo verificare che size sia 0
        assertEquals(0, new Biblioteca().dimensione(), "La biblioteca dovrebbe essere vuota");
        Biblioteca.elencoLibri(); // Verifica manualmente che stampi "Non ci sono libri disponibili"
    }

    @Test
    public void testElencoLibriConElementi() {
        // Arrange
        Libro libro1 = new Libro("1984", "George Orwell", 1949, 9780451524935L);
        Libro libro2 = new Libro("Il Nome della Rosa", "Umberto Eco", 1980, 9788804665298L);
        Biblioteca.aggiungi(libro1);
        Biblioteca.aggiungi(libro2);

        // Act & Assert
        assertEquals(2, new Biblioteca().dimensione(), "La dimensione dovrebbe essere 2");
        Biblioteca.elencoLibri(); // Verifica manualmente che stampi i due libri
    }
}