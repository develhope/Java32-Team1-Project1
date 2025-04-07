package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrestitoTest {

    private Libro libro;
    private Utente utente;

    @BeforeEach
    public void setUp() {
        // Resetta lo stato della biblioteca prima di ogni test
        Biblioteca.size = 0;
        for (int i = 0; i < Biblioteca.dati.length; i++) {
            Biblioteca.dati[i] = null;
        }

        // Inizializza oggetti di test
        libro = new Libro("1984", "George Orwell", 1949, 9780451524935L);
        utente = new Utente("Mario","Rossi",1);
    }

    @Test
    public void testTrovaIndiceLibroPresente() {
        // Arrange
        Biblioteca.aggiungi(libro);

        // Act
        int indice = Prestito.trovaIndiceLibro(libro);

        // Assert
        assertEquals(0, indice, "L'indice del libro dovrebbe essere 0");
    }

    @Test
    public void testTrovaIndiceLibroNonPresente() {
        // Arrange: Biblioteca vuota o senza il libro
        Libro altroLibro = new Libro("Il Nome della Rosa", "Umberto Eco", 1980, 9788804665298L);

        // Act
        int indice = Prestito.trovaIndiceLibro(altroLibro);

        // Assert
        assertEquals(-1, indice, "L'indice dovrebbe essere -1 se il libro non è presente");
    }

    @Test
    public void testAssociaLibroAUtenteConSuccesso() {
        // Arrange
        Biblioteca.aggiungi(libro);

        // Act
        boolean risultato = Prestito.associaLibroAUtente(libro, utente);

        // Assert
        assertTrue(risultato, "Il prestito dovrebbe riuscire");
        // Verifica manuale dell'output: "1984 è stato preso in prestito da Mario Rossi"
    }

    @Test
    public void testAssociaLibroAUtenteLibroNonPresente() {
        // Arrange: Biblioteca vuota

        // Act
        boolean risultato = Prestito.associaLibroAUtente(libro, utente);

        // Assert
        assertFalse(risultato, "Il prestito dovrebbe fallire se il libro non è presente");
        // Verifica manuale dell'output: "Il libro 1984 non è disponibile per il prestito."
    }

    @Test
    public void testRestituisciLibroConSuccesso() {
        // Arrange
        Biblioteca.aggiungi(libro);
        Prestito.associaLibroAUtente(libro, utente); // Simula il prestito
        Biblioteca.rimuovi(0); // Simula la rimozione del libro dalla biblioteca

        // Act
        boolean risultato = Prestito.restituisciLibro(libro, utente);

        // Assert
        assertTrue(risultato, "La restituzione dovrebbe riuscire");
        assertEquals(1, new Biblioteca().dimensione(), "Il libro dovrebbe essere riaggiunto alla biblioteca");
        // Verifica manuale dell'output: "Mario Rossi ha restituito 1984"
    }

    @Test
    public void testRestituisciLibroNonPrestato() {
        // Arrange: Biblioteca vuota

        // Act
        boolean risultato = Prestito.restituisciLibro(libro, utente);

        // Assert
        assertFalse(risultato, "La restituzione dovrebbe fallire se il libro non è stato prestato");
        // Verifica manuale dell'output: "Impossibile restituire il libro 1984, potrebbe non essere stato preso in prestito."
    }

    @Test
    public void testEseguiPrestitoConSuccesso() {
        // Arrange
        Biblioteca.aggiungi(libro);

        // Act
        boolean risultato = Prestito.eseguiPrestito(libro, utente);

        // Assert
        assertTrue(risultato, "Il prestito dovrebbe riuscire");
        // Verifica manuale dell'output: "1984 è stato preso in prestito da Mario Rossi"
    }

    @Test
    public void testEseguiPrestitoLibroNonDisponibile() {
        // Arrange: Biblioteca vuota


        // Act
        boolean risultato = Prestito.eseguiPrestito(libro, utente);

        // Assert
        assertFalse(risultato, "Il prestito dovrebbe fallire se il libro non è disponibile");
        // Verifica manuale dell'output: "Il libro 1984 non è disponibile per il prestito"
    }
}