package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Biblioteca} class.
 * This class contains unit tests to verify the functionality of the Biblioteca class,
 * including adding, removing, retrieving books, managing loans, and searching for books by title.
 */
public class BibliotecaTest {

    private Biblioteca biblioteca;
    private Libro libro1;
    private Libro libro2;
    private Utente utente;
    private Prestito prestito;

    /**
     * Sets up the test environment before each test.
     * Initializes a new {@link Biblioteca}, two {@link Libro} instances, an {@link Utente}, and a {@link Prestito}.
     */
    @BeforeEach
    public void setUp() {
        biblioteca = new Biblioteca();
        libro1 = new Libro("Il Signore degli Anelli", "J.R.R. Tolkien" , 1984 , 234657891027485L );
        libro2 = new Libro("1984", "George Orwell", 1984, 37895007386964L);
        utente = new Utente("Mario", "Rossi", 238);
        prestito = new Prestito(libro1, utente);
    }

    /**
     * Tests the {@link Biblioteca#aggiungi(Libro)} method.
     * Verifies that a book can be added to the library and that the size increases accordingly.
     */
    @Test
    public void testAggiungiLibro() {
        biblioteca.aggiungi(libro1);
        assertEquals(1, biblioteca.dimensione(), "The library size should be 1 after adding a book.");
        assertEquals(libro1, biblioteca.get(0), "The added book should be retrievable at index 0.");
    }

    /**
     * Tests the {@link Biblioteca#rimuovi(int)} method.
     * Verifies that a book can be removed from the library and that the size decreases accordingly.
     */
    @Test
    public void testRimuoviLibro() {
        biblioteca.aggiungi(libro1);
        biblioteca.aggiungi(libro2);
        biblioteca.rimuovi(0);
        assertEquals(1, biblioteca.dimensione(), "The library size should be 1 after removing a book.");
        assertEquals(libro2, biblioteca.get(0), "The remaining book should be the second one added.");
    }

    /**
     * Tests the {@link Biblioteca#dimensione()} method.
     * Verifies that the size of the library is correctly reported.
     */
    @Test
    public void testDimensione() {
        assertEquals(0, biblioteca.dimensione(), "The library should be empty initially.");
        biblioteca.aggiungi(libro1);
        assertEquals(1, biblioteca.dimensione(), "The library size should be 1 after adding a book.");
    }

    /**
     * Tests the {@link Biblioteca#get(int)} method.
     * Verifies that a book can be retrieved by its index.
     */
    @Test
    public void testGetLibro() {
        biblioteca.aggiungi(libro1);
        assertEquals(libro1, biblioteca.get(0), "The book at index 0 should be the one added.");
    }

    /**
     * Tests the {@link Biblioteca#isDisponibilita(Libro)} method.
     * Verifies that a book is marked as available when not on loan and unavailable when on loan.
     */
    @Test
    public void testIsDisponibilita() {
        assertTrue(biblioteca.isDisponibilita(libro1), "The book should be available initially.");
        biblioteca.aggiungiPrestito(prestito);
        assertFalse(biblioteca.isDisponibilita(libro1), "The book should be unavailable when on loan.");
    }

    /**
     * Tests the {@link Biblioteca#aggiungiPrestito(Prestito)} method.
     * Verifies that a loan can be added and that attempting to loan an already loaned book throws an exception.
     */
    @Test
    public void testAggiungiPrestito() {
        biblioteca.aggiungiPrestito(prestito);
        assertFalse(biblioteca.isDisponibilita(libro1), "The book should be unavailable after being loaned.");

        Prestito altroPrestito = new Prestito(libro1, new Utente("Luigi", "Verdi", 665));
        assertThrows(IllegalArgumentException.class, () -> biblioteca.aggiungiPrestito(altroPrestito),
                "Adding a loan for an already loaned book should throw an exception.");
    }
    /**
     * Tests the {@link Biblioteca#rimuoviPrestito(Prestito)} method.
     * Verifies that a loan can be removed and that attempting to remove a non-existent loan throws an exception.
     */
    @Test
    public void testRimuoviPrestito() {
        biblioteca.aggiungiPrestito(prestito);
        biblioteca.rimuoviPrestito(prestito);
        assertTrue(biblioteca.isDisponibilita(libro1), "The book should be available after the loan is removed.");

        assertThrows(IllegalArgumentException.class, () -> biblioteca.rimuoviPrestito(prestito),
                "Removing a non-existent loan should throw an exception.");
    }

    /**
     * Tests the {@link Biblioteca#cercaLibroPerTitolo(String)} method.
     * Verifies that a book can be found by its title and that a non-existent title returns null.
     */




    @Test
    public void testCercaLibroPerTitolo() {
        Libro libro1 = new Libro("Il Signore degli Anelli", "Tolkien", 1954 ,1234567890683087L);
        biblioteca.aggiungi(libro1);

        assertEquals(libro1, biblioteca.cercaLibroPerTitolo("Il Signore degli Anelli"));
        assertNull(biblioteca.cercaLibroPerTitolo("Un titolo che non esiste"));
    }


    /**
     * Tests the {@link Biblioteca#elencoLibri()} method.
     * Verifies that the list of books is printed correctly (tested indirectly via size and availability).
     */
    @Test
    public void testElencoLibri() {
        biblioteca.aggiungi(libro1);
        biblioteca.aggiungi(libro2);
        biblioteca.aggiungiPrestito(prestito);
        // Since elencoLibri prints to console, we verify indirectly by checking size and availability
        assertEquals(2, biblioteca.dimensione(), "The library should contain 2 books.");
        assertFalse(biblioteca.isDisponibilita(libro1), "The first book should be unavailable.");
        assertTrue(biblioteca.isDisponibilita(libro2), "The second book should be available.");
    }
}
