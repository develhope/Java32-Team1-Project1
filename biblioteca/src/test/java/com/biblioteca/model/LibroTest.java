package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per la classe {@link Libro}.
 * Contiene test unitari per verificare il funzionamento della classe Libro,
 * inclusi costruttori, metodi getter e setter, toString, equals e hashCode.
 */
public class LibroTest {

    private Libro libro;
    private final String titolo = "Il Signore degli Anelli";
    private final String autore = "J.R.R. Tolkien";
    private final int annoPubblicazione = 1954;
    private final long isbn = 9788804688372L;

    /**
     * Configura l'ambiente di test prima di ogni test.
     * Inizializza una nuova istanza di {@link Libro} con valori predefiniti.
     */
    @BeforeEach
    public void configura() {
        libro = new Libro(titolo, autore, annoPubblicazione, isbn);
    }

    /**
     * Testa il costruttore di default della classe {@link Libro}.
     * Verifica che i valori predefiniti per titolo, autore, anno di pubblicazione e ISBN siano impostati correttamente.
     */
    @Test
    public void testCostruttoreDefault() {
        Libro libroDefault = new Libro();
        assertEquals("titolo", libroDefault.getTitolo(), "Il titolo predefinito dovrebbe essere 'titolo'.");
        assertEquals("sconoscuto", libroDefault.getAutore(), "L'autore predefinito dovrebbe essere 'sconoscuto'.");
        assertEquals(0, libroDefault.getAnnoPubblicazione(), "L'anno di pubblicazione predefinito dovrebbe essere 0000.");
        assertEquals(0L, libroDefault.getISBN(), "L'ISBN predefinito dovrebbe essere 0000000000000000.");
    }

    /**
     * Testa il costruttore parametrizzato della classe {@link Libro}.
     * Verifica che i valori forniti per titolo, autore, anno di pubblicazione e ISBN siano impostati correttamente.
     */
    @Test
    public void testCostruttoreParametrizzato() {
        assertEquals(titolo, libro.getTitolo(), "Il titolo dovrebbe corrispondere al valore fornito.");
        assertEquals(autore, libro.getAutore(), "L'autore dovrebbe corrispondere al valore fornito.");
        assertEquals(annoPubblicazione, libro.getAnnoPubblicazione(), "L'anno di pubblicazione dovrebbe corrispondere al valore fornito.");
        assertEquals(isbn, libro.getISBN(), "L'ISBN dovrebbe corrispondere al valore fornito.");
    }

    /**
     * Testa i metodi {@link Libro#setTitolo(String)} e {@link Libro#getTitolo()}.
     * Verifica che il titolo possa essere aggiornato e recuperato correttamente.
     */
    @Test
    public void testSetAndGetTitolo() {
        String nuovoTitolo = "1984";
        libro.setTitolo(nuovoTitolo);
        assertEquals(nuovoTitolo, libro.getTitolo(), "Il titolo dovrebbe essere aggiornato al nuovo valore.");
    }

    /**
     * Testa i metodi {@link Libro#setAutore(String)} e {@link Libro#getAutore()}.
     * Verifica che l'autore possa essere aggiornato e recuperato correttamente.
     */
    @Test
    public void testSetAndGetAutore() {
        String nuovoAutore = "George Orwell";
        libro.setAutore(nuovoAutore);
        assertEquals(nuovoAutore, libro.getAutore(), "L'autore dovrebbe essere aggiornato al nuovo valore.");
    }

    /**
     * Testa i metodi {@link Libro#setAnnoPubblicazione(int)} e {@link Libro#getAnnoPubblicazione()}.
     * Verifica che l'anno di pubblicazione possa essere aggiornato e recuperato correttamente.
     */
    @Test
    public void testSetAndGetAnnoPubblicazione() {
        int nuovoAnno = 2020;
        libro.setAnnoPubblicazione(nuovoAnno);
        assertEquals(nuovoAnno, libro.getAnnoPubblicazione(), "L'anno di pubblicazione dovrebbe essere aggiornato al nuovo valore.");
    }

    /**
     * Testa i metodi {@link Libro#setISBN(long)} e {@link Libro#getISBN()}.
     * Verifica che l'ISBN possa essere aggiornato e recuperato correttamente.
     */
    @Test
    public void testSetAndGetISBN() {
        long nuovoISBN = 9780141036144L;
        libro.setISBN(nuovoISBN);
        assertEquals(nuovoISBN, libro.getISBN(), "L'ISBN dovrebbe essere aggiornato al nuovo valore.");
    }

    /**
     * Testa il metodo {@link Libro#toString()}.
     * Verifica che la rappresentazione testuale dell'oggetto Libro contenga le informazioni attese.
     */
    @Test
    public void testToString() {
        String attesa = "Libro{titolo='" + titolo + "', autore='" + autore + "', annoPubblicazione=" + annoPubblicazione + ", ISBN=" + isbn + "'}";
        assertEquals(attesa, libro.toString(), "toString dovrebbe restituire la corretta rappresentazione testuale.");
    }

    /**
     * Testa il metodo {@link Libro#equals(Object)}.
     * Verifica che due oggetti Libro con lo stesso ISBN siano considerati uguali,
     * e che oggetti con ISBN diversi o di tipi diversi non siano uguali.
     */
    @Test
    public void testEquals() {
        Libro stessoLibro = new Libro("Altro Titolo", "Altro Autore", 2000, isbn);
        Libro libroDiverso = new Libro("1984", "George Orwell", 1949, 9780141036144L);

        assertTrue(libro.equals(stessoLibro), "I libri con lo stesso ISBN dovrebbero essere uguali.");
        assertFalse(libro.equals(libroDiverso), "I libri con ISBN diversi non dovrebbero essere uguali.");
        assertFalse(libro.equals(null), "Un libro non dovrebbe essere uguale a null.");
        assertFalse(libro.equals(new Object()), "Un libro non dovrebbe essere uguale a un oggetto di tipo diverso.");
        assertTrue(libro.equals(libro), "Un libro dovrebbe essere uguale a se stesso.");
    }

    /**
     * Testa il metodo {@link Libro#hashCode()}.
     * Verifica che due oggetti Libro con lo stesso ISBN abbiano lo stesso hash code,
     * e che oggetti con ISBN diversi abbiano hash code diversi.
     */
    @Test
    public void testHashCode() {
        Libro stessoLibro = new Libro("Altro Titolo", "Altro Autore", 2000, isbn);
        Libro libroDiverso = new Libro("1984", "George Orwell", 1949, 9780141036144L);

        assertEquals(libro.hashCode(), stessoLibro.hashCode(), "I libri con lo stesso ISBN dovrebbero avere lo stesso hash code.");
        assertNotEquals(libro.hashCode(), libroDiverso.hashCode(), "I libri con ISBN diversi dovrebbero avere hash code diversi.");
    }
}