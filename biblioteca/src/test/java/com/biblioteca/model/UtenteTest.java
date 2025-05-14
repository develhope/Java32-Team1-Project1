package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Utente} class.
 * This class contains unit tests to verify the functionality of the Utente class,
 * including constructor behavior, getter and setter methods, and the overridden equals, hashCode, toString, and stampaDettagliUtente methods.
 */
public class UtenteTest {

    private Utente utente1;
    private Utente utente2;

    /**
     * Sets up the test environment before each test.
     * Initializes sample users for testing.
     */
    @BeforeEach
    public void setUp() {
        utente1 = new Utente("Mario", "Rossi", 1);
        utente2 = new Utente("Mario", "Rossi", 1); // Same as utente1 for equals testing
    }

    /**
     * Tests the {@link Utente#Utente(String, String, int)} constructor.
     * Verifies that a user is correctly initialized with the provided name, surname, and ID.
     */
    @Test
    public void testCostruttore() {
        assertEquals("Mario", utente1.getNome(), "The name should match the one provided.");
        assertEquals("Rossi", utente1.getCognome(), "The surname should match the one provided.");
        assertEquals(1, utente1.getIdUtente(), "The user ID should match the one provided.");
    }

    /**
     * Tests the {@link Utente#Utente(String, String, int)} constructor with null arguments.
     * Verifies that the constructor accepts null values for name and surname (as it does not explicitly check for them).
     */
    @Test
    public void testCostruttoreConNull() {
        Utente utenteConNull = new Utente(null, null, 2);
        assertNull(utenteConNull.getNome(), "The name should be null when constructed with null.");
        assertNull(utenteConNull.getCognome(), "The surname should be null when constructed with null.");
        assertEquals(2, utenteConNull.getIdUtente(), "The user ID should match the one provided.");
    }

    /**
     * Tests the {@link Utente#getNome()} and {@link Utente#setNome(String)} methods.
     * Verifies that the name can be retrieved and updated correctly.
     */
    @Test
    public void testGetSetNome() {
        assertEquals("Mario", utente1.getNome(), "The getNome method should return the correct name.");
        utente1.setNome("Luigi");
        assertEquals("Luigi", utente1.getNome(), "The setNome method should update the name correctly.");
    }

    /**
     * Tests the {@link Utente#getCognome()} and {@link Utente#setCognome(String)} methods.
     * Verifies that the surname can be retrieved and updated correctly.
     */
    @Test
    public void testGetSetCognome() {
        assertEquals("Rossi", utente1.getCognome(), "The getCognome method should return the correct surname.");
        utente1.setCognome("Bianchi");
        assertEquals("Bianchi", utente1.getCognome(), "The setCognome method should update the surname correctly.");
    }

    /**
     * Tests the {@link Utente#getIdUtente()} and {@link Utente#setIdUtente(int)} methods.
     * Verifies that the user ID can be retrieved and updated correctly.
     */
    @Test
    public void testGetSetIdUtente() {
        assertEquals(1, utente1.getIdUtente(), "The getIdUtente method should return the correct ID.");
        utente1.setIdUtente(3);
        assertEquals(3, utente1.getIdUtente(), "The setIdUtente method should update the ID correctly.");
    }

    /**
     * Tests the {@link Utente#equals(Object)} method.
     * Verifies that two users with the same name, surname, and ID are considered equal,
     * and users with different attributes are not equal.
     */
    @Test
    public void testEquals() {
        // Same name, surname, and ID
        assertEquals(utente1, utente2, "Users with the same name, surname, and ID should be equal.");
        assertEquals(utente1.hashCode(), utente2.hashCode(), "Equal users should have the same hash code.");

        // Different name
        Utente utenteDiversoNome = new Utente("Luigi", "Rossi", 1);
        assertNotEquals(utente1, utenteDiversoNome, "Users with different names should not be equal.");

        // Different surname
        Utente utenteDiversoCognome = new Utente("Mario", "Bianchi", 1);
        assertNotEquals(utente1, utenteDiversoCognome, "Users with different surnames should not be equal.");

        // Different ID
        Utente utenteDiversoId = new Utente("Mario", "Rossi", 2);
        assertNotEquals(utente1, utenteDiversoId, "Users with different IDs should not be equal.");

        // Null and different class
        assertNotEquals(utente1, null, "A user should not be equal to null.");
        assertNotEquals(utente1, new Object(), "A user should not be equal to an object of a different class.");

        // Same object
        assertEquals(utente1, utente1, "A user should be equal to itself.");
    }

    /**
     * Tests the {@link Utente#hashCode()} method.
     * Verifies that equal users produce the same hash code.
     */
    @Test
    public void testHashCode() {
        assertEquals(utente1.hashCode(), utente2.hashCode(), "Equal users should have the same hash code.");
        Utente utenteDiverso = new Utente("Luigi", "Bianchi", 2);
        assertNotEquals(utente1.hashCode(), utenteDiverso.hashCode(), "Different users should have different hash codes.");
    }

    /**
     * Tests the {@link Utente#toString()} method.
     * Verifies that the string representation contains the expected information.
     */
    @Test
    public void testToString() {
        String result = utente1.toString();
        assertTrue(result.contains("nome='Mario'"), "The toString should include the name.");
        assertTrue(result.contains("cognome='Rossi'"), "The toString should include the surname.");
        assertTrue(result.contains("idUtente=1"), "The toString should include the user ID.");
        assertTrue(result.startsWith("Utente{"), "The toString should start with 'Utente{'.");
    }

    /**
     * Tests the {@link Utente#stampaDettagliUtente()} method.
     * Verifies that the method prints the expected user details to the console.
     */
    @Test
    public void testStampaDettagliUtente() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            utente1.stampaDettagliUtente();
            String output = outContent.toString();
            assertTrue(output.contains("Nome: Mario"), "The output should include the name.");
            assertTrue(output.contains("Cognome: Rossi"), "The output should include the surname.");
            assertTrue(output.contains("ID Utente: 1"), "The output should include the user ID.");
        } finally {
            // Restore System.out
            System.setOut(originalOut);
        }
    }
}