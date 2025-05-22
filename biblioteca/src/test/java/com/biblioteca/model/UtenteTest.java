package com.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtenteTest {

    @Test
    void testCostruttoreEGetter() {
        Utente utente = new Utente("Mario", "Rossi", 1);

        assertEquals("Mario", utente.getNome());
        assertEquals("Rossi", utente.getCognome());
        assertEquals(1, utente.getIdUtente());
    }

    @Test
    void testSetter() {
        Utente utente = new Utente("Mario", "Rossi", 1);
        utente.setNome("Luigi");
        utente.setCognome("Verdi");
        utente.setIdUtente(2);

        assertEquals("Luigi", utente.getNome());
        assertEquals("Verdi", utente.getCognome());
        assertEquals(2, utente.getIdUtente());
    }

    @Test
    void testEqualsEHashCode() {
        Utente utente1 = new Utente("Anna", "Bianchi", 10);
        Utente utente2 = new Utente("Anna", "Bianchi", 10);
        Utente utente3 = new Utente("Anna", "Bianchi", 11);

        assertEquals(utente1, utente2);
        assertEquals(utente1.hashCode(), utente2.hashCode());
        assertNotEquals(utente1, utente3);
    }

    @Test
    void testToString() {
        Utente utente = new Utente("Giulia", "Neri", 99);
        String expected = "Utente{nome='Giulia', cognome='Neri', idUtente=99}";
        assertEquals(expected, utente.toString());
    }
}
