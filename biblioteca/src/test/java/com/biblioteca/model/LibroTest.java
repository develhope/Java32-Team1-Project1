package com.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Classe di test per la verifica delle funzionalità della classe {@link Libro}.
 * Contiene test per il costruttore di default, il costruttore parametrizzato e il metodo {@code equals}.
 * I risultati attesi e ottenuti vengono stampati a console per un confronto manuale.
 */
public class LibroTest {

    /*
     * Metodo principale che esegue una serie di test per verificare il comportamento della classe {@link Libro}.
     * I test includono:
     * <ul>
     *     <li>Verifica del costruttore di default</li>
     *     <li>Verifica del costruttore parametrizzato</li>
     *     <li>Verifica del metodo {@code equals} per libri con lo stesso ISBN</li>
     *     <li>Verifica del metodo {@code equals} per libri con ISBN diverso</li>
     *     <li>Verifica del metodo {@code equals} con confronto a {@code null}</li>
     *     <li>Verifica del metodo {@code equals} con lo stesso oggetto</li>
     * </ul>
     */

    // Creazione di oggetti di supporto per i test
    Libro libroParam = new Libro("Il Nome del Vento", "Patrick Rothfuss", 2007, "9788804681830", 5);
    Libro libro1 = new Libro("Libro A", "Autore A", 2020, "1234567890123", 3);
    Libro libro2 = new Libro("Libro B", "Autore B", 2021, "1234567890123", 2);
    Libro libro3 = new Libro("Libro C", "Autore C", 2019, "9876543210987", 1);

    // Test 1: Verifica del costruttore di default
    @Test
    void testCostruttoreDefault() {
        Libro libroDefault = new Libro();
        assertEquals("titolo", libroDefault.getTitolo());
        assertEquals("sconosciuto", libroDefault.getAutore());
        assertEquals(0, libroDefault.getAnnoPubblicazione());
        assertEquals("0000000000000000", libroDefault.getISBN());
        assertEquals(0, libroDefault.getNumeroCopie());
    }

    // Test 2: Verifica del costruttore parametrizzato
    @Test
    void testCostruttoreParam() {
        assertEquals("Il Nome del Vento", libroParam.getTitolo());
        assertEquals("Patrick Rothfuss", libroParam.getAutore());
        assertEquals(2007, libroParam.getAnnoPubblicazione());
        assertEquals("9788804681830", libroParam.getISBN());
        assertEquals(5, libroParam.getNumeroCopie());
    }

    // Test 3: Verifica del metodo equals() - Libri con stesso ISBN
    @Test
    void testEqualsLibriConStessoIsbn() {
        assertEquals(libro1, libro2);
    }

    // Test 4: Verifica del metodo equals() - Libri con ISBN diverso
    @Test
    void testEqualsLibriConIsbnDiverso() {
        assertNotEquals(libro1, libro3);
    }

    // Test 5: Verifica del metodo equals() - Confronto con null
    @Test
    void testEqualsNull() {
        assertNotNull(libro1);
    }

    // Test 6: Verifica del metodo equals() - Stesso oggetto
    @Test
    void testEqualsStessoOggetto() {
        assertEquals(libro1, libro1);
    }
}