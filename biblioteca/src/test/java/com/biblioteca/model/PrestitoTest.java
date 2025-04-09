package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrestitoTest {

    private Biblioteca biblioteca;
    private Libro libro1;
    private Libro libro2;
    private Utente utente1;
    private Utente utente2;

    @BeforeEach
    public void setUp() {
        // Inizializza la biblioteca e svuota i dati precedenti
        biblioteca = new Biblioteca();
        Biblioteca.dati = new Libro[10]; // Supponiamo un array di dimensione 10 per semplicità
        Biblioteca.size = 0;

        // Crea libri e utenti per i test
        libro1 = new Libro("Il Signore degli Anelli", "J.R.R. Tolkien", 1988, 12345678901234L);
        libro2 = new Libro("Sapore di rose", "George Orwell", 1984, 12345678901235L);
        utente1 = new Utente("Mario", " Rossi", 4);
        utente2 = new Utente("Anna", "Verdi", 1 );

        // Aggiungi un libro alla biblioteca
        Biblioteca.aggiungi(libro1);
    }

    @Test
    public void testEseguiPrestitoLibroDisponibile() {
        // Testa il prestito di un libro disponibile
        Prestito.eseguiPrestito(libro1, utente1);

        // Verifica che il libro sia stato rimosso dalla biblioteca
        assertEquals(-1, Prestito.trovaIndiceLibro(libro1), "Il libro dovrebbe essere rimosso dalla biblioteca");
        // Verifica che il prestito sia registrato
        Prestito.associaLibroAUtente(libro1);
    }

    @Test
    public void testEseguiPrestitoLibroNonDisponibile() {
        // Esegui il prestito del libro1
        Prestito.eseguiPrestito(libro1, utente1);

        // Tenta di prestare di nuovo lo stesso libro
        Prestito.eseguiPrestito(libro1, utente2);

        // Verifica che il libro non sia più disponibile
        assertEquals(-1, Prestito.trovaIndiceLibro(libro1), "Il libro non dovrebbe essere nella biblioteca");
    }

    @Test
    public void testAssociaLibroAUtenteLibroPrestato() {
        // Esegui il prestito
        Prestito.eseguiPrestito(libro1, utente1);

        // Verifica l'associazione
        Prestito.associaLibroAUtente(libro1);
    }

    @Test
    public void testAssociaLibroAUtenteLibroNonPrestato() {
        // Tenta di verificare l'associazione di un libro non prestato
        Prestito.associaLibroAUtente(libro2);
    }

    @Test
    public void testRestituisciLibroCorretto() {
        // Esegui il prestito
        Prestito.eseguiPrestito(libro1, utente1);

        // Restituisci il libro
        boolean risultato = Prestito.restituisciLibro(libro1, utente1);

        // Verifica che la restituzione sia avvenuta con successo
        assertTrue(risultato, "La restituzione dovrebbe riuscire");
        assertNotEquals(-1, Prestito.trovaIndiceLibro(libro1), "Il libro dovrebbe essere tornato nella biblioteca");
    }

    @Test
    public void testRestituisciLibroUtenteSbagliato() {
        // Esegui il prestito con utente1
        Prestito.eseguiPrestito(libro1, utente1);

        // Tenta di restituire il libro con utente2
        boolean risultato = Prestito.restituisciLibro(libro1, utente2);

        // Verifica che la restituzione fallisca
        assertFalse(risultato, "La restituzione dovrebbe fallire per utente sbagliato");
        assertEquals(-1, Prestito.trovaIndiceLibro(libro1), "Il libro non dovrebbe essere tornato nella biblioteca");
    }

    @Test
    public void testRestituisciLibroNonPrestato() {
        // Tenta di restituire un libro mai prestato
        boolean risultato = Prestito.restituisciLibro(libro2, utente1);

        // Verifica che la restituzione fallisca
        assertFalse(risultato, "La restituzione dovrebbe fallire per libro non prestato");
    }

    @Test
    public void testTrovaIndiceLibroEsistente() {
        // Verifica che il libro1 sia trovabile nella biblioteca
        int indice = Prestito.trovaIndiceLibro(libro1);
        assertNotEquals(-1, indice, "Il libro dovrebbe essere trovato nella biblioteca");
    }

    @Test
    public void testTrovaIndiceLibroNonEsistente() {
        // Verifica che un libro non presente restituisca -1
        int indice = Prestito.trovaIndiceLibro(libro2);
        assertEquals(-1, indice, "Il libro non dovrebbe essere trovato nella biblioteca");
    }
}
