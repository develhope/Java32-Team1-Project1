package com.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtenteTest {

    @Test
    void testCostruttoreEGetter() {
        Utente utente = new Utente(new String("Mario"), new String("Rossi"), 1);

        assertEquals("Mario", utente.getNome());
        assertEquals("Rossi", utente.getCognome());
        assertEquals(1, utente.getIdUtente());
    }

    @Test
    void testSetter() {
        Utente utente = new Utente(new String("Mario"), new String("Rossi"), 1);
        utente.setNome("Luigi");
        utente.setCognome("Verdi");
        utente.setIdUtente(2);

        assertEquals("Luigi", utente.getNome());
        assertEquals("Verdi", utente.getCognome());
        assertEquals(2, utente.getIdUtente());
    }

    @Test
    void testEqualsEHashCode() {
        Utente utente1 = new Utente(new String ("Anna"), new String("Bianchi"), 10);
        Utente utente2 = new Utente(new String ("Anna"), new String("Bianchi"), 10);
        Utente utente3 = new Utente(new String ("Anna"), new String("Bianchi"), 11);

        assertEquals(utente1, utente2);
        assertEquals(utente1.hashCode(), utente2.hashCode());
        assertNotEquals(utente1, utente3);
    }

}
