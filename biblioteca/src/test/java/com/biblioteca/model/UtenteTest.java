package com.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtenteTest {

    @Test
    public void testCostruttore() {
        // Arrange & Act
        Utente utente = new Utente("Mario", "Rossi", 1);

        // Assert
        assertEquals("Mario", utente.getNome(), "Il nome dovrebbe essere quello passato al costruttore");
        assertEquals("Rossi", utente.getCognome(), "Il cognome dovrebbe essere quello passato al costruttore");
        assertEquals(1, utente.getIdUtente(), "L'ID utente dovrebbe essere quello passato al costruttore");
    }

    @Test
    public void testSetterEGetter() {
        // Arrange
        Utente utente = new Utente("Mario", "Rossi", 1);

        // Act
        utente.setNome("Luigi");
        utente.setCognome("Bianchi");
        utente.setIdUtente(2);

        // Assert
        assertEquals("Luigi", utente.getNome(), "Il nome dovrebbe essere aggiornato dal setter");
        assertEquals("Bianchi", utente.getCognome(), "Il cognome dovrebbe essere aggiornato dal setter");
        assertEquals(2, utente.getIdUtente(), "L'ID utente dovrebbe essere aggiornato dal setter");
    }

    @Test
    public void testDettagliUtente() {
        // Arrange
        Utente utente = new Utente("Mario", "Rossi", 1);

        // Act & Assert
        // Non possiamo testare direttamente l'output di System.out, ma possiamo chiamare il metodo
        utente.dettagliUtente(); // Verifica manuale: "Nome: Mario Cognome: Rossi ID Utente: 1"
        assertEquals("Mario", utente.getNome(), "Il nome dovrebbe rimanere invariato dopo dettagliUtente");
    }

    @Test
    public void testCreazioneConValoriLimite() {
        // Arrange & Act
        Utente utente = new Utente("", "", Integer.MAX_VALUE);

        // Assert
        assertEquals("", utente.getNome(), "Il nome può essere una stringa vuota");
        assertEquals("", utente.getCognome(), "Il cognome può essere una stringa vuota");
        assertEquals(Integer.MAX_VALUE, utente.getIdUtente(), "L'ID utente dovrebbe accettare il valore massimo di int");
    }

    @Test
    public void testIndipendenzaIstanze() {
        // Arrange
        Utente utente1 = new Utente("Mario", "Rossi", 1);
        Utente utente2 = new Utente("Luigi", "Bianchi", 2);

        // Act
        utente1.setNome("Giovanni");

        // Assert
        assertEquals("Giovanni", utente1.getNome(), "Il nome di utente1 dovrebbe essere aggiornato");
        assertEquals("Luigi", utente2.getNome(), "Il nome di utente2 dovrebbe rimanere invariato");
        assertEquals("Rossi", utente1.getCognome(), "Il cognome di utente1 dovrebbe rimanere invariato");
        assertEquals("Bianchi", utente2.getCognome(), "Il cognome di utente2 dovrebbe rimanere invariato");
    }
}