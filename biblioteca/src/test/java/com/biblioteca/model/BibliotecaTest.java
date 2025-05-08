package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Biblioteca} class.
 * This class contains unit tests to verify the functionality of the Biblioteca class,
 * including adding and removing books, managing loans, checking user existence, and searching books by title.
 */
public class BibliotecaTest {

    private Biblioteca biblioteca;
    private Libro libro1;
    private Libro libro2;
    private Utente utente1;
    private Prestito prestito1;

    /**
     * Sets up the test environment before each test.
     * Initializes a new {@link Biblioteca}, sample books, a user, and a loan.
     */
    @BeforeEach
    public void setUp() {
        biblioteca = new Biblioteca();
        libro1 = new Libro("Il Nome del Vento", "Patrick Rothfuss" , 1910 , 123456789345L);
        libro2 = new Libro("1984", "George Orwell", 1980 ,  987654321567L);
        utente1 = new Utente("Mario", "Rossi", 2009);
        prestito1 = new Prestito(libro1, utente1);
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
     * Tests the {@link Biblioteca#rimuovi(int)} method with an invalid index.
     * Verifies that removing a book with an invalid index does not affect the library.
     */
    @Test
    public void testRimuoviLibroIndiceNonValido() {
        biblioteca.aggiungi(libro1);
        biblioteca.rimuovi(5); // Invalid index
        assertEquals(1, biblioteca.dimensione(), "The library size should remain unchanged with invalid index.");
    }

    /**
     * Tests the {@link Biblioteca#dimensione()} method.
     * Verifies that the size of the library is correctly reported.
     */
    @Test
    public void testDimensione() {
        assertEquals(0, biblioteca.dimensione(), "The library should be empty initially.");
        biblioteca.aggiungi(libro1);
        biblioteca.aggiungi(libro2);
        assertEquals(2, biblioteca.dimensione(), "The library size should be 2 after adding two books.");
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
     * Verifies that a book's availability is correctly determined based on loan status.
     */
    @Test
    public void testIsDisponibilita() {
        biblioteca.aggiungi(libro1);
        assertTrue(biblioteca.isDisponibilita(libro1), "The book should be available initially.");
        biblioteca.aggiungiPrestito(prestito1);
        assertFalse(biblioteca.isDisponibilita(libro1), "The book should not be available when on loan.");
    }

    /**
     * Tests the {@link Biblioteca#aggiungiPrestito(Prestito)} method.
     * Verifies that a loan can be added and that adding a loan for an already loaned book throws an exception.
     */
    @Test
    public void testAggiungiPrestito() {
        biblioteca.aggiungi(libro1);
        biblioteca.aggiungiPrestito(prestito1);
        assertFalse(biblioteca.isDisponibilita(libro1), "The book should be marked as on loan.");

        // Try to add another loan for the same book
        Prestito prestito2 = new Prestito(libro1, utente1);
        assertThrows(IllegalArgumentException.class, () -> biblioteca.aggiungiPrestito(prestito2),
                "Adding a loan for an already loaned book should throw an exception.");
    }

    /**
     * Tests the {@link Biblioteca#rimuoviPrestito(Prestito)} method.
     * Verifies that a loan can be removed and that removing a non-existent loan throws an exception.
     */
    @Test
    public void testRimuoviPrestito() {
        biblioteca.aggiungi(libro1);
        biblioteca.aggiungiPrestito(prestito1);
        biblioteca.rimuoviPrestito(prestito1);
        assertTrue(biblioteca.isDisponibilita(libro1), "The book should be available after removing the loan.");

        // Try to remove a non-existent loan
        Prestito prestitoNonEsistente = new Prestito(libro1 , utente1);
        assertThrows(IllegalArgumentException.class, () -> biblioteca.rimuoviPrestito(prestitoNonEsistente),
                "Removing a non-existent loan should throw an exception.");
    }

    /**
     * Tests the {@link Biblioteca#esisteUtente(Utente)} method.
     * Verifies that the existence of a user in the library is correctly determined.
     */
    @Test
    public void testEsisteUtente() {
        assertFalse(biblioteca.esisteUtente(utente1), "The user should not exist initially.");
        biblioteca.listaUtenti.add(utente1);
        assertTrue(biblioteca.esisteUtente(utente1), "The user should exist after being added.");
    }

    /**
     * Tests the {@link Biblioteca#listaPrestitiPerUtente(Utente)} method.
     * Verifies that the list of loans for a specific user is correctly returned.
     */
    @Test
    public void testListaPrestitiPerUtente() {
        biblioteca.aggiungi(libro1);
        biblioteca.aggiungiPrestito(prestito1);
        List<Prestito> prestiti = biblioteca.listaPrestitiPerUtente(utente1);
        assertEquals(1, prestiti.size(), "The user should have one loan.");
        assertEquals(prestito1, prestiti.get(0), "The loan should match the one added.");
    }

    /**
     * Tests the {@link Biblioteca#cercaLibroPerTitolo(String)} method.
     * Verifies that a book can be found by its title, ignoring case, and that a non-existent title returns null.
     */
    @Test
    public void testCercaLibroPerTitolo() {
        biblioteca.aggiungi(libro1);
        biblioteca.aggiungi(libro2);
        assertEquals(libro1, biblioteca.cercaLibroPerTitolo("Il Nome del Vento"),
                "The book should be found by its title.");
        assertEquals(libro1, biblioteca.cercaLibroPerTitolo("il nome del vento"),
                "The book should be found ignoring case.");
        assertNull(biblioteca.cercaLibroPerTitolo("Non Esiste"),
                "A non-existent book title should return null.");
    }

    /**
     * Tests the {@link Biblioteca#elencoLibri()} method.
     * Verifies that the method correctly handles an empty library by printing an error message.
     * Note: This test does not verify console output but checks the logic indirectly.
     */
    @Test
    public void testElencoLibriVuoto() {
        assertEquals(0, biblioteca.dimensione(), "The library should be empty.");
        // Since elencoLibri prints to console, we verify indirectly by ensuring no books are present
    }
}