package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Prestito} class.
 * This class contains unit tests to verify the functionality of the Prestito class,
 * including constructor behavior, getter methods, and the overridden equals, hashCode, and toString methods.
 */
public class PrestitoTest {

    private Libro libro1;
    private Libro libro2;
    private Utente utente1;
    private Utente utente2;
    private Prestito prestito1;
    private Prestito prestito2;

    /**
     * Sets up the test environment before each test.
     * Initializes sample books, users, and loans for testing.
     */
    @BeforeEach
    public void setUp() {
        libro1 = new Libro("Il Nome del Vento", "Patrick Rothfuss", 1910, 123456789758L);
        libro2 = new Libro("1984", "George Orwell", 1980 ,  987654321567L);
        utente1 = new Utente("Mario", "Rossi", 001);
        utente2 = new Utente("Luigi", "Bianchi", 002);
        prestito1 = new Prestito(libro1, utente1);
        prestito2 = new Prestito(libro1, utente1); // Same as prestito1 for equals testing
    }

    /**
     * Tests the {@link Prestito#Prestito(Libro, Utente)} constructor.
     * Verifies that a loan is correctly initialized with the provided book and user.
     */
    @Test
    public void testCostruttore() {
        assertEquals(libro1, prestito1.getLibro(), "The book in the loan should match the one provided.");
        assertEquals(utente1, prestito1.getUtente(), "The user in the loan should match the one provided.");
    }

    /**
     * Tests the {@link Prestito#Prestito(Libro, Utente)} constructor with null arguments.
     * Verifies that the constructor accepts null values (as it does not explicitly check for them).
     */
    @Test
    public void testCostruttoreConNull() {
        Prestito prestitoConNull = new Prestito(null, null);
        assertNull(prestitoConNull.getLibro(), "The book should be null when constructed with null.");
        assertNull(prestitoConNull.getUtente(), "The user should be null when constructed with null.");
    }

    /**
     * Tests the {@link Prestito#getLibro()} method.
     * Verifies that the correct book is returned.
     */
    @Test
    public void testGetLibro() {
        assertEquals(libro1, prestito1.getLibro(), "The getLibro method should return the correct book.");
    }

    /**
     * Tests the {@link Prestito#getUtente()} method.
     * Verifies that the correct user is returned.
     */
    @Test
    public void testGetUtente() {
        assertEquals(utente1, prestito1.getUtente(), "The getUtente method should return the correct user.");
    }

    /**
     * Tests the {@link Prestito#equals(Object)} method.
     * Verifies that two loans with the same book and user are considered equal,
     * and loans with different books or users are not equal.
     */
    @Test
    public void testEquals() {
        // Same book and user
        assertEquals(prestito1, prestito2, "Loans with the same book and user should be equal.");
        assertEquals(prestito1.hashCode(), prestito2.hashCode(), "Equal loans should have the same hash code.");

        // Different book
        Prestito prestitoDiversoLibro = new Prestito(libro2, utente1);
        assertNotEquals(prestito1, prestitoDiversoLibro, "Loans with different books should not be equal.");

        // Different user
        Prestito prestitoDiversoUtente = new Prestito(libro1, utente2);
        assertNotEquals(prestito1, prestitoDiversoUtente, "Loans with different users should not be equal.");

        // Null and different class
        assertNotEquals(prestito1, null, "A loan should not be equal to null.");
        assertNotEquals(prestito1, new Object(), "A loan should not be equal to an object of a different class.");

        // Same object
        assertEquals(prestito1, prestito1, "A loan should be equal to itself.");
    }

    /**
     * Tests the {@link Prestito#hashCode()} method.
     * Verifies that equal loans produce the same hash code.
     */
    @Test
    public void testHashCode() {
        assertEquals(prestito1.hashCode(), prestito2.hashCode(), "Equal loans should have the same hash code.");
        Prestito prestitoDiverso = new Prestito(libro2, utente2);
        assertNotEquals(prestito1.hashCode(), prestitoDiverso.hashCode(), "Different loans should have different hash codes.");
    }

    /**
     * Tests the {@link Prestito#toString()} method.
     * Verifies that the string representation contains the expected information.
     */
    @Test
    public void testToString() {
        String result = prestito1.toString();
        assertTrue(result.contains("utente=" + utente1.toString()), "The toString should include the user.");
        assertTrue(result.contains("libro=" + libro1.toString()), "The toString should include the book.");
        assertTrue(result.startsWith("Prestito{"), "The toString should start with 'Prestito{'.");
    }
}