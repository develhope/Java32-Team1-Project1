package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per la classe {@link Prestito}.
 * Contiene test unitari per verificare il funzionamento della classe Prestito,
 * inclusi costruttore, metodi getter, toString, equals e hashCode.
 */
public class PrestitoTest {

    private Prestito prestito;
    private Libro libro;
    private Utente utente;

    /**
     * Configura l'ambiente di test prima di ogni test.
     * Inizializza una nuova istanza di {@link Libro}, {@link Utente} e {@link Prestito} con valori predefiniti.
     */
    @BeforeEach
    public void configura() {
        libro = new Libro("Il Signore degli Anelli", "J.R.R. Tolkien", 1954, 9788804688372L);
        utente = new Utente("Mario" , "Rossi", 668);
        prestito = new Prestito(libro, utente);
    }

    /**
     * Testa il costruttore della classe {@link Prestito}.
     * Verifica che il libro e l'utente siano correttamente assegnati al prestito.
     */
    @Test
    public void testCostruttore() {
        assertEquals(libro, prestito.getLibro(), "Il libro dovrebbe corrispondere al valore fornito.");
        assertEquals(utente, prestito.getUtente(), "L'utente dovrebbe corrispondere al valore fornito.");
    }

    /**
     * Testa il metodo {@link Prestito#getLibro()}.
     * Verifica che il libro associato al prestito sia restituito correttamente.
     */
    @Test
    public void testGetLibro() {
        assertEquals(libro, prestito.getLibro(), "Il metodo getLibro dovrebbe restituire il libro corretto.");
    }

    /**
     * Testa il metodo {@link Prestito#getUtente()}.
     * Verifica che l'utente associato al prestito sia restituito correttamente.
     */
    @Test
    public void testGetUtente() {
        assertEquals(utente, prestito.getUtente(), "Il metodo getUtente dovrebbe restituire l'utente corretto.");
    }

    /**
     * Testa il metodo {@link Prestito#toString()}.
     * Verifica che la rappresentazione testuale dell'oggetto Prestito contenga le informazioni attese.
     */
    @Test
    public void testToString() {
        String attesa = "Prestito{utente=" + utente.toString() + ", libro=" + libro.toString() + "}";
        assertEquals(attesa, prestito.toString(), "toString dovrebbe restituire la corretta rappresentazione testuale.");
    }

    /**
     * Testa il metodo {@link Prestito#equals(Object)}.
     * Verifica che due oggetti Prestito con lo stesso libro e utente siano considerati uguali,
     * e che oggetti con libro o utente diversi o di tipi diversi non siano uguali.
     */
    @Test
    public void testEquals() {
        // Stesso libro e utente
        Prestito stessoPrestito = new Prestito(libro, utente);
        // Libro o utente diversi
        Libro altroLibro = new Libro("1984", "George Orwell", 1949, 9780141036144L);
        Utente altroUtente = new Utente("Luigi", "Verdi", 667);
        Prestito prestitoDiverso1 = new Prestito(altroLibro, utente);
        Prestito prestitoDiverso2 = new Prestito(libro, altroUtente);

        assertTrue(prestito.equals(stessoPrestito), "I prestiti con lo stesso libro e utente dovrebbero essere uguali.");
        assertFalse(prestito.equals(prestitoDiverso1), "I prestiti con libri diversi non dovrebbero essere uguali.");
        assertFalse(prestito.equals(prestitoDiverso2), "I prestiti con utenti diversi non dovrebbero essere uguali.");
        assertFalse(prestito.equals(null), "Un prestito non dovrebbe essere uguale a null.");
        assertFalse(prestito.equals(new Object()), "Un prestito non dovrebbe essere uguale a un oggetto di tipo diverso.");
        assertTrue(prestito.equals(prestito), "Un prestito dovrebbe essere uguale a se stesso.");
    }
}

/**
 * Testa il metodo {@link Prestito#hashCode()}.
 * Verifica che due oggetti Prestito con lo stesso libro e utente abbiano lo stesso hash code,
 * e che oggetti con libro o utente
 */