package com.biblioteca.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.time.LocalDateTime;

/**
 * Classe di test per la verifica delle funzionalità della classe {@link Prestito}.
 * Contiene test per i costruttori, il metodo {@code toString} e il metodo {@code equals}.
 * I risultati attesi e ottenuti vengono stampati a console per un confronto manuale.
 */
public class PrestitoTest {

    /**
     * Metodo principale che esegue una serie di test per verificare il comportamento della classe {@link Prestito}.
     * I test includono:
     * <ul>
     *     <li>Verifica del costruttore con ID</li>
     *     <li>Verifica del costruttore senza ID</li>
     *     <li>Verifica del metodo {@code equals} per prestiti con stesso libro e utente</li>
     *     <li>Verifica del metodo {@code equals} per prestiti con libro o utente diverso</li>
     *     <li>Verifica del metodo {@code equals} con confronto a {@code null}</li>
     *     <li>Verifica del metodo {@code equals} con lo stesso oggetto</li>
     * </ul>
     * I risultati dei test vengono stampati a console per consentire una verifica visiva.
     */

    // Creazione di oggetti di supporto per i test
    Libro libro1 = new Libro("Il Nome del Vento", "Patrick Rothfuss", 2007, "9788804681830", 5);
    Libro libro2 = new Libro("Libro Diverso", "Autore Diverso", 2010, "9876543210987", 2);
    Utente utente1 = new Utente("Mario", "Rossi", 2); // Assumo costruttore (nome, cognome, codiceFiscale)
    Utente utente2 = new Utente("Luigi", "Bianchi", 1);
    LocalDateTime dataPrestito = LocalDateTime.of(2025, 5, 26, 10, 0);
    LocalDateTime dataRestituzione = LocalDateTime.of(2025, 6, 9, 10, 0);
    Prestito prestito1 = new Prestito(1, libro1, utente1, dataPrestito, dataRestituzione);
    Prestito prestito2 = new Prestito(2, libro1, utente1, dataPrestito.plusDays(1), dataRestituzione.plusDays(1));
    Prestito prestito3 = new Prestito(3, libro2, utente1, dataPrestito, dataRestituzione);
    Prestito prestito4 = new Prestito(4, libro1, utente2, dataPrestito, dataRestituzione);

    // Test 1: Verifica del costruttore con ID
    @Test
    void testVerificaCostruttoreID() {
        Prestito prestitoConId = new Prestito(1, libro1, utente1, dataPrestito, dataRestituzione);
        System.out.println("Test 1 - Costruttore con ID:");
        System.out.println("Atteso: idPrestito=1, utente=" + utente1 + ", libro=" + libro1 + ", dataPrestito=2025-05-26T10:00, dataRestituzione=2025-06-09T10:00");
        System.out.println("Ottenuto: " + prestitoConId);
        System.out.println();
    }

    // Test 2: Verifica del costruttore senza ID
    @Test
    void testVerificaCostruttoreSenzaID() {
        Prestito prestitoSenzaId = new Prestito(libro1, utente1, dataPrestito, dataRestituzione);
        System.out.println("Test 2 - Costruttore senza ID:");
        System.out.println("Atteso: idPrestito=null, utente=" + utente1 + ", libro=" + libro1 + ", dataPrestito=2025-05-26T10:00, dataRestituzione=2025-06-09T10:00");
        System.out.println("Ottenuto: " + prestitoSenzaId);
        System.out.println();
    }

    // Test 3: Verifica del metodo equals() - Prestiti con stesso libro e utente
    @Test
    void testEqualsLibroConUtente() {
        System.out.println("Test 3 - Equals (stesso libro e utente):");
        System.out.println("Atteso: true");
        System.out.println("Ottenuto: " + prestito1.equals(prestito2));
        System.out.println();
    }

    // Test 4: Verifica del metodo equals() - Prestiti con libro o utente diverso
    @Test
    void testEqualsLibroConUtenteDiverso() {
        System.out.println("Test 4 - Equals (libro o utente diverso):");
        System.out.println("Atteso: false (diverso libro), false (diverso utente)");
        System.out.println("Ottenuto: " + prestito1.equals(prestito3) + ", " + prestito1.equals(prestito4));
        System.out.println();
    }

    // Test 5: Verifica del metodo equals() - Confronto con null
    @Test
    void testEqualsNull() {
        System.out.println("Test 5 - Equals (confronto con null):");
        System.out.println("Atteso: false");
        System.out.println("Ottenuto: " + prestito1.equals(null));
        System.out.println();
    }

    // Test 6: Verifica del metodo equals() - Stesso oggetto
    @Test
    void testEqualsStessoOggetto() {
        System.out.println("Test 6 - Equals (stesso oggetto):");
        System.out.println("Atteso: true");
        System.out.println("Ottenuto: " + prestito1.equals(prestito1));
    }
}