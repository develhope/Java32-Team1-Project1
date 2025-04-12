package com.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe di test per la classe {@link Libro}.
 * Questa classe contiene test unitari per verificare le funzionalità della classe Libro,
 * inclusi il costruttore di default, il costruttore parametrizzato, i metodi setter/getter e il metodo toString.
 */

public class LibroTest {


   /**
     * Verifica il costruttore di default della classe Libro.
     * Controlla che i valori iniziali di titolo, autore, anno di pubblicazione e ISBN siano quelli previsti.
     */  
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


     /**
     * Verifica il costruttore parametrizzato della classe Libro.
     * Controlla che i valori di titolo, autore, anno di pubblicazione e ISBN corrispondano a quelli forniti.
     */
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


     /**
     * Verifica i metodi setter e getter della classe Libro.
     * Controlla che i valori impostati tramite i setter siano correttamente recuperati dai getter.
     */
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


    /**
     * Verifica il metodo toString della classe Libro.
     * Controlla che la rappresentazione testuale dell'oggetto Libro sia formattata correttamente.
     */
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
