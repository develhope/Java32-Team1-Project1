package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe di test per la classe {@link Biblioteca}.
 * Questa classe contiene test unitari per verificare le funzionalità della classe Biblioteca,
 * inclusi l'aggiunta, la rimozione, il recupero e l'elenco dei libri.
 */
public class BibliotecaTest {

    /**
     * Configura l'ambiente di test prima di ogni test.
     * Reimposta la dimensione della biblioteca a 0 e svuota l'array dei dati.
     */
    @BeforeEach
    public void setUp() {
        // Resetta lo stato della biblioteca prima di ogni test
        Biblioteca.size = 0;
        for (int i = 0; i < Biblioteca.dati.length; i++) {
            Biblioteca.dati[i] = null;
        }
    }

    
     /**
     * Verifica l'aggiunta di un singolo libro alla biblioteca.
     * Controlla che la dimensione aumenti di 1 e che il libro aggiunto possa essere recuperato correttamente.
     */
    @Test
    public void testAggiungiLibro() {
        // Arrange
        Libro libro = new Libro("Il Nome della Rosa", "Umberto Eco", 1980, 9788804665298L);

        // Act
        Biblioteca.aggiungi(libro);

        // Assert
        assertEquals(1, new Biblioteca().dimensione(), "La dimensione dovrebbe essere 1 dopo aver aggiunto un libro");
        assertEquals(libro, new Biblioteca().get(0), "Il libro recuperato dovrebbe essere lo stesso aggiunto");
    }

    
     /**
     * Verifica il comportamento quando si tenta di aggiungere un libro oltre la capacità della biblioteca (100).
     * Controlla che la dimensione non aumenti oltre la capacità massima.
     */
    @Test
    public void testAggiungiLibroOltreCapacita() {
        // Arrange: Riempi l'array oltre la capacità (100)
        for (int i = 0; i < 100; i++) {
            Biblioteca.aggiungi(new Libro("Titolo" + i, "Autore", 2023, 1234567890000L + i));
        }
        int dimensioneIniziale = new Biblioteca().dimensione();
        Libro libroExtra = new Libro("Extra", "Autore", 2023, 9999999999999L);

        // Act
        Biblioteca.aggiungi(libroExtra);

        // Assert
        assertEquals(dimensioneIniziale, new Biblioteca().dimensione(), "La dimensione non dovrebbe aumentare oltre 100");
    }


    /**
     * Verifica la rimozione di un libro dalla biblioteca.
     * Controlla che la dimensione diminuisca di 1 e che il libro rimanente sia corretto.
     */
    @Test
    public void testRimuoviLibro() {
        // Arrange
        Libro libro1 = new Libro("1984", "George Orwell", 1949, 9780451524935L);
        Libro libro2 = new Libro("Il Nome della Rosa", "Umberto Eco", 1980, 9788804665298L);
        Biblioteca.aggiungi(libro1);
        Biblioteca.aggiungi(libro2);

        // Act
        Biblioteca.rimuovi(0);

        // Assert
        assertEquals(1, new Biblioteca().dimensione(), "La dimensione dovrebbe essere 1 dopo la rimozione");
        assertEquals(libro2, new Biblioteca().get(0), "Il libro rimanente dovrebbe essere il secondo aggiunto");
    }


     /**
     * Verifica l'elenco dei libri quando la biblioteca è vuota.
     * Controlla che la dimensione sia 0 (verifica manuale necessaria per l'output su console).
     */
    @Test
    public void testRimuoviDaBibliotecaVuota() {
        // Act
        Biblioteca.rimuovi(0);

        // Assert
        assertEquals(0, new Biblioteca().dimensione(), "La dimensione dovrebbe rimanere 0 se la biblioteca è vuota");
    }


    /**
     * Verifica il recupero di un libro dalla biblioteca.
     * Controlla che il libro recuperato corrisponda a quello aggiunto.
     */
    @Test
    public void testGetLibro() {
        // Arrange
        Libro libro = new Libro("1984", "George Orwell", 1949, 9780451524935L);
        Biblioteca.aggiungi(libro);

        // Act
        Libro recuperato = new Biblioteca().get(0);

        // Assert
        assertEquals(libro, recuperato, "Il libro recuperato dovrebbe essere uguale a quello aggiunto");
    }


    /**
     * Verifica l'elenco dei libri quando la biblioteca è vuota.
     * Controlla che la dimensione sia 0 (verifica manuale necessaria per l'output su console).
     */

    @Test
    public void testElencoLibriVuoto() {
        // Act & Assert
        // Non possiamo testare direttamente l'output di System.err, ma possiamo verificare che size sia 0
        assertEquals(0, new Biblioteca().dimensione(), "La biblioteca dovrebbe essere vuota");
        Biblioteca.elencoLibri(); // Verifica manualmente che stampi "Non ci sono libri disponibili"
    }


    /**
     * Verifica l'elenco dei libri quando la biblioteca è vuota.
     * Controlla che la dimensione sia 0 (verifica manuale necessaria per l'output su console).
     */

    @Test
    public void testElencoLibriConElementi() {
        // Arrange
        Libro libro1 = new Libro("1984", "George Orwell", 1949, 9780451524935L);
        Libro libro2 = new Libro("Il Nome della Rosa", "Umberto Eco", 1980, 9788804665298L);
        Biblioteca.aggiungi(libro1);
        Biblioteca.aggiungi(libro2);

        // Act & Assert
        assertEquals(2, new Biblioteca().dimensione(), "La dimensione dovrebbe essere 2");
        Biblioteca.elencoLibri(); // Verifica manualmente che stampi i due libri
    }
}
