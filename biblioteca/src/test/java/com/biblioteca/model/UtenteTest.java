package com.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per la classe {@link Utente}.
 * Contiene test unitari per verificare il funzionamento della classe Utente,
 * inclusi costruttore, metodi getter e setter, stampaDettagliUtente e toString.
 */
public class UtenteTest {

    private Utente utente;
    private final String nome = "Mario";
    private final String cognome = "Rossi";
    private final int idUtente = 1001;

    /**
     * Configura l'ambiente di test prima di ogni test.
     * Inizializza una nuova istanza di {@link Utente} con valori predefiniti.
     */
    @BeforeEach
    public void configura() {
        utente = new Utente(nome, cognome, idUtente);
    }

    /**
     * Testa il costruttore della classe {@link Utente}.
     * Verifica che i valori forniti per nome, cognome e ID utente siano impostati correttamente.
     */
    @Test
    public void testCostruttore() {
        assertEquals(nome, utente.getNome(), "Il nome dovrebbe corrispondere al valore fornito.");
        assertEquals(cognome, utente.getCognome(), "Il cognome dovrebbe corrispondere al valore fornito.");
        assertEquals(idUtente, utente.getIdUtente(), "L'ID utente dovrebbe corrispondere al valore fornito.");
    }

    /**
     * Testa i metodi {@link Utente#setNome(String)} e {@link Utente#getNome()}.
     * Verifica che il nome possa essere aggiornato e recuperato correttamente.
     */
    @Test
    public void testSetAndGetNome() {
        String nuovoNome = "Luigi";
        utente.setNome(nuovoNome);
        assertEquals(nuovoNome, utente.getNome(), "Il nome dovrebbe essere aggiornato al nuovo valore.");
    }

    /**
     * Testa i metodi {@link Utente#setCognome(String)} e {@link Utente#getCognome()}.
     * Verifica che il cognome possa essere aggiornato e recuperato correttamente.
     */
    @Test
    public void testSetAndGetCognome() {
        String nuovoCognome = "Verdi";
        utente.setCognome(nuovoCognome);
        assertEquals(nuovoCognome, utente.getCognome(), "Il cognome dovrebbe essere aggiornato al nuovo valore.");
    }

    /**
     * Testa i metodi {@link Utente#setIdUtente(int)} e {@link Utente#getIdUtente()}.
     * Verifica che l'ID utente possa essere aggiornato e recuperato correttamente.
     */
    @Test
    public void testSetAndGetIdUtente() {
        int nuovoId = 1002;
        utente.setIdUtente(nuovoId);
        assertEquals(nuovoId, utente.getIdUtente(), "L'ID utente dovrebbe essere aggiornato al nuovo valore.");
    }

    /**
     * Testa il metodo {@link Utente#stampaDettagliUtente()}.
     * Verifica che i dettagli dell'utente siano stampati correttamente sulla console.
     */
    @Test
    public void testStampaDettagliUtente() {
        // Reindirizza System.out a un ByteArrayOutputStream per catturare l'output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Esegue il metodo
        utente.stampaDettagliUtente();

        // Costruisce l'output atteso
        String outputAtteso = "Nome: " + nome + System.lineSeparator() +
                "Cognome: " + cognome + System.lineSeparator() +
                "ID Utente: " + idUtente + System.lineSeparator();

        // Verifica l'output
        assertEquals(outputAtteso, outContent.toString(), "L'output di stampaDettagliUtente dovrebbe corrispondere al formato atteso.");

        // Ripristina System.out
        System.setOut(originalOut);
    }

    /**
     * Testa il metodo {@link Utente#toString()}.
     * Verifica che la rappresentazione testuale dell'oggetto Utente contenga le informazioni attese.
     */
    @Test
    public void testToString() {
        // 1. Crea un oggetto Utente
        Utente utente = new Utente("Mario", "Rossi", 100);

        // 2. Costruisci la stringa attesa
        String atteso = "Utente{nome='Mario', cognome='Rossi', idUtente=100}";

        // 3. Confronta
        assertEquals(atteso, utente.toString(), "toString dovrebbe restituire la corretta rappresentazione testuale.");
    }
}