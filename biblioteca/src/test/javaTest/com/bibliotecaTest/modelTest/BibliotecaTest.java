package com.bibliotecaTest.modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BibliotecaTest {

    @BeforeEach
    public void setup() {
        Biblioteca.dati = new Libro[100];
        Biblioteca.size = 0;
    }

    @Test
    public void testAggiungi() {
        Libro libro = new Libro("Il Signore degli Anelli", "J.R.R. Tolkien",1954,1234567890123L);
        Biblioteca.aggiungi(libro);

        assertEquals(1, Biblioteca.size);
        assertEquals(libro, Biblioteca.get(0));
    }

    @Test
    public void testRimuovi() {
        Libro libro1 = new Libro("Il Signore degli Anelli", "J.R.R. Tolkien",1954,1234567890123L);
        Libro libro2 = new Libro("Harry Potter", "J.K. Rowling",1999,123464562735265L);

        Biblioteca.aggiungi(libro1);
        Biblioteca.aggiungi(libro2);

        Biblioteca.rimuovi(0);

        assertEquals(1, Biblioteca.size);
        assertEquals(libro2, Biblioteca.get(0));
    }

    @Test
    public void testDimensione() {
        assertEquals(0, Biblioteca.size);

        Biblioteca.aggiungi(new Libro("Il Nome della Rosa", "Umberto Eco",1980,124534254352256L));
        assertEquals(1, Biblioteca.dimensione());
    }

    @Test
    public void testElencoLibri() {
        Biblioteca.aggiungi(new Libro("Il Nome della Rosa", "Umberto Eco",1980,124534254352286L));
        Biblioteca.aggiungi(new Libro("1984", "George Orwell",1954,123634254352256L));

        assertEquals(2, Biblioteca.dimensione());
    }
}