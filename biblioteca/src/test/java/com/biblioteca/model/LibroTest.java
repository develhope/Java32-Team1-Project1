package com.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

    @Test
    public void testCostruttoreDefault() {
        // Arrange & Act
        Libro libro = new Libro();

        // Assert
        assertEquals("titolo", libro.getTitolo(), "Il titolo dovrebbe essere 'titolo' per il costruttore di default");
        assertEquals("sconoscuto", libro.getAutore(), "L'autore dovrebbe essere 'sconoscuto' per il costruttore di default");
        assertEquals(0, libro.getAnnoPubblicazione(), "L'anno di pubblicazione dovrebbe essere 0 per il costruttore di default");
        assertEquals(0L, libro.getISBN(), "L'ISBN dovrebbe essere 0 per il costruttore di default");
    }

    @Test
    public void testCostruttoreParametrizzato() {
        // Arrange & Act
        Libro libro = new Libro("1984", "George Orwell", 1949, 9780451524935L);

        // Assert
        assertEquals("1984", libro.getTitolo(), "Il titolo dovrebbe corrispondere al valore passato");
        assertEquals("George Orwell", libro.getAutore(), "L'autore dovrebbe corrispondere al valore passato");
        assertEquals(1949, libro.getAnnoPubblicazione(), "L'anno di pubblicazione dovrebbe corrispondere al valore passato");
        assertEquals(9780451524935L, libro.getISBN(), "L'ISBN dovrebbe corrispondere al valore passato");
    }

    @Test
    public void testSetterEGetter() {
        // Arrange
        Libro libro = new Libro();

        // Act
        libro.setTitolo("Il Nome della Rosa");
        libro.setAutore("Umberto Eco");
        libro.setAnnoPubblicazione(1980);
        libro.setISBN(9788804665298L);


        // Assert
        assertEquals("Il Nome della Rosa", libro.getTitolo(), "Il titolo dovrebbe essere aggiornato dal setter");
        assertEquals("Umberto Eco", libro.getAutore(), "L'autore dovrebbe essere aggiornato dal setter");
        assertEquals(1980, libro.getAnnoPubblicazione(), "L'anno di pubblicazione dovrebbe essere aggiornato dal setter");
        assertEquals(9788804665298L, libro.getISBN(), "L'ISBN dovrebbe essere aggiornato dal setter");
    }

    @Test
    public void testToString() {
        // Arrange
        Libro libro = new Libro("1984", "George Orwell", 1949, 9780451524935L);

        // Act
        String risultato = libro.toString();

        // Assert
        String atteso = "Libro{Titolo='1984', Autore='George Orwell', Anno di Pubblicazione=1949, ISBN='9780451524935'}";
        assertEquals(atteso, risultato, "Il metodo toString dovrebbe restituire una stringa formattata correttamente");
    }
}