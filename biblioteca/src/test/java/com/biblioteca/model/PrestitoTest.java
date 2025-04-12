package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe di test per la classe {@link Prestito}.
 * Questa classe contiene test unitari per verificare le funzionalità di gestione dei prestiti,
 * inclusi l'esecuzione del prestito, l'associazione di libri agli utenti, la restituzione dei libri
 * e la ricerca di libri nella biblioteca.
 */
public class PrestitoTest {

    private Biblioteca biblioteca;
    private Libro libro1;
    private Libro libro2;
    private Utente utente1;
    private Utente utente2;


        /**
     * Configura l'ambiente di test prima di ogni test.
     * Inizializza la biblioteca, svuota i dati precedenti, crea libri e utenti per i test
     * e aggiunge un libro alla biblioteca.
     */

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


    /**
     * Verifica l'esecuzione del prestito di un libro disponibile.
     * Controlla che il libro venga rimosso dalla biblioteca e che il prestito sia registrato.
     */
    @Test
    public void testEseguiPrestitoLibroDisponibile() {
        // Testa il prestito di un libro disponibile
        Prestito.eseguiPrestito(libro1, utente1);

        // Verifica che il libro sia stato rimosso dalla biblioteca
        assertEquals(-1, Prestito.trovaIndiceLibro(libro1), "Il libro dovrebbe essere rimosso dalla biblioteca");
        // Verifica che il prestito sia registrato
        Prestito.associaLibroAUtente(libro1);
    }


    /**
     * Verifica il tentativo di prestito di un libro non disponibile.
     * Controlla che il libro, una volta prestato, non sia più presente nella biblioteca.
     */
    @Test
    public void testEseguiPrestitoLibroNonDisponibile() {
        // Esegui il prestito del libro1
        Prestito.eseguiPrestito(libro1, utente1);

        // Tenta di prestare di nuovo lo stesso libro
        Prestito.eseguiPrestito(libro1, utente2);

        // Verifica che il libro non sia più disponibile
        assertEquals(-1, Prestito.trovaIndiceLibro(libro1), "Il libro non dovrebbe essere nella biblioteca");
    }


    /**
     * Verifica l'associazione di un libro a un utente dopo il prestito.
     * Controlla che l'associazione venga registrata correttamente per un libro prestato.
     */
    @Test
    public void testAssociaLibroAUtenteLibroPrestato() {
        // Esegui il prestito
        Prestito.eseguiPrestito(libro1, utente1);

        // Verifica l'associazione
        Prestito.associaLibroAUtente(libro1);
    }


     /**
     * Verifica l'associazione di un libro non prestato a un utente.
     * Controlla il comportamento quando si tenta di associare un libro non in prestito.
     */
    @Test
    public void testAssociaLibroAUtenteLibroNonPrestato() {
        // Tenta di verificare l'associazione di un libro non prestato
        Prestito.associaLibroAUtente(libro2);
    }


    /**
     * Verifica la restituzione corretta di un libro prestato.
     * Controlla che la restituzione abbia successo e che il libro ritorni nella biblioteca.
     */
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


    /**
     * Verifica il tentativo di restituzione di un libro da parte di un utente sbagliato.
     * Controlla che la restituzione fallisca e che il libro rimanga non disponibile.
     */
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


     /**
     * Verifica il tentativo di restituzione di un libro non prestato.
     * Controlla che la restituzione fallisca.
     */
    @Test
    public void testRestituisciLibroNonPrestato() {
        // Tenta di restituire un libro mai prestato
        boolean risultato = Prestito.restituisciLibro(libro2, utente1);

        // Verifica che la restituzione fallisca
        assertFalse(risultato, "La restituzione dovrebbe fallire per libro non prestato");
    }


    /**
     * Verifica la ricerca di un libro esistente nella biblioteca.
     * Controlla che il libro venga trovato correttamente.
     */
    @Test
    public void testTrovaIndiceLibroEsistente() {
        // Verifica che il libro1 sia trovabile nella biblioteca
        int indice = Prestito.trovaIndiceLibro(libro1);
        assertNotEquals(-1, indice, "Il libro dovrebbe essere trovato nella biblioteca");
    }


     /**
     * Verifica la ricerca di un libro non esistente nella biblioteca.
     * Controlla che venga restituito -1 per un libro non presente.
     */
    @Test
    public void testTrovaIndiceLibroNonEsistente() {
        // Verifica che un libro non presente restituisca -1
        int indice = Prestito.trovaIndiceLibro(libro2);
        assertEquals(-1, indice, "Il libro non dovrebbe essere trovato nella biblioteca");
    }
}
