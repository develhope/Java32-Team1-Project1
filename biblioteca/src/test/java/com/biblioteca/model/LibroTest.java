package com.biblioteca.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    @Test
    void testCostruttoreDiDefault() {
        Libro libro = new Libro();
        assertEquals("titolo", libro.getTitolo());
        assertEquals("sconoscuto", libro.getAutore());
        assertEquals(0, libro.getAnnoPubblicazione());
        assertEquals("0000000000000000", libro.getISBN());
    }

    @Test
    void testCostruttoreParametrico() {
        Libro libro = new Libro("1984", "George Orwell", 1949, "1234567890123");
        assertEquals("1984", libro.getTitolo());
        assertEquals("George Orwell", libro.getAutore());
        assertEquals(1949, libro.getAnnoPubblicazione());
        assertEquals("1234567890123", libro.getISBN());
    }

    @Test
    void testSettersAndGetters() {
        Libro libro = new Libro();
        libro.setTitolo("Il nome della rosa");
        libro.setAutore("Umberto Eco");
        libro.setAnnoPubblicazione(1980);
        libro.setISBN("9876543210987");

        assertEquals("Il nome della rosa", libro.getTitolo());
        assertEquals("Umberto Eco", libro.getAutore());
        assertEquals(1980, libro.getAnnoPubblicazione());
        assertEquals("9876543210987", libro.getISBN());
    }

    @Test
    void testEqualsConStessoISBN() {
        Libro libro1 = new Libro("Libro A", "Autore A", 2000, "ISBN123");
        Libro libro2 = new Libro("Libro B", "Autore B", 2020, "ISBN123");

        // Corretto: confronto basato sul contenuto dell'ISBN
        assertTrue(libro1.equals(libro2));
    }

    @Test
    void testEqualsConISBNDiversi() {
        Libro libro1 = new Libro("Libro A", "Autore A", 2000, "ISBN123");
        Libro libro2 = new Libro("Libro B", "Autore B", 2020, "ISBN456");

        assertFalse(libro1.equals(libro2));
    }

    @Test
    void testToString() {
        Libro libro = new Libro("Dune", "Frank Herbert", 1965, "12345");
        String output = libro.toString();
        assertTrue(output.contains("Dune"));
        assertTrue(output.contains("Frank Herbert"));
        assertTrue(output.contains("1965"));
        assertTrue(output.contains("12345"));
    }
}
