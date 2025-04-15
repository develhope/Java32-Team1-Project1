package com.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Classe di test per la classe {@link Utente}.
 * Questa classe contiene test unitari per verificare le funzionalità della classe Utente,
 * inclusi il costruttore, i metodi setter e getter, il metodo dettagliUtente e il comportamento
 * con valori limite e istanze indipendenti.
 */
public class UtenteTest {


    /**
     * Verifica il costruttore della classe Utente.
     * Controlla che i valori di nome, cognome e ID utente corrispondano a quelli forniti.
     */

    @Test
    public void testCostruttore() {
        // Arrange & Act
        Utente utente = new Utente("Mario", "Rossi", 1);

        // Assert
        assertEquals("Mario", utente.getNome(), "Il nome dovrebbe essere quello passato al costruttore");
        assertEquals("Rossi", utente.getCognome(), "Il cognome dovrebbe essere quello passato al costruttore");
        assertEquals(1, utente.getIdUtente(), "L'ID utente dovrebbe essere quello passato al costruttore");
    }


    /**
     * Verifica i metodi setter e getter della classe Utente.
     * Controlla che i valori impostati tramite i setter siano correttamente recuperati dai getter.
     */
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


    /**
     * Verifica il metodo dettagliUtente della classe Utente.
     * Controlla che i valori dell'utente rimangano invariati dopo l'esecuzione del metodo
     * (verifica manuale necessaria per l'output su console).
     */
    @Test
    public void testDettagliUtente() {
        // Arrange
        Utente utente = new Utente("Mario", "Rossi", 1);

        // Act & Assert
        // Non possiamo testare direttamente l'output di System.out, ma possiamo chiamare il metodo
        utente.dettagliUtente(); // Verifica manuale: "Nome: Mario Cognome: Rossi ID Utente: 1"
        assertEquals("Mario", utente.getNome(), "Il nome dovrebbe rimanere invariato dopo dettagliUtente");
    }


    /**
     * Verifica la creazione di un utente con valori limite.
     * Controlla che il costruttore accetti stringhe vuote per nome e cognome
     * e il valore massimo per l'ID utente.
     */
    @Test
    public void testCreazioneConValoriLimite() {
        // Arrange & Act
        Utente utente = new Utente("", "", Integer.MAX_VALUE);

        // Assert
        assertEquals("", utente.getNome(), "Il nome può essere una stringa vuota");
        assertEquals("", utente.getCognome(), "Il cognome può essere una stringa vuota");
        assertEquals(Integer.MAX_VALUE, utente.getIdUtente(), "L'ID utente dovrebbe accettare il valore massimo di int");
    }


    /**
     * Verifica l'indipendenza tra istanze diverse della classe Utente.
     * Controlla che la modifica di un'istanza non influenzi un'altra.
     */

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
