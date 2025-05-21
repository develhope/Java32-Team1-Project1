package com.biblioteca.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrestitoTest {

    @Test
    void testCostruttoreEGetter() {
        Libro libro = new Libro("1984", "George Orwell", 1949, "1234567890123");
        Utente utente = new Utente("Mario", "Rossi", 1);
        Date dataPrestito = Date.valueOf(LocalDate.now());
        Date dataRestituzione = Date.valueOf(LocalDate.now().plusDays(14));

        Prestito prestito = new Prestito(libro, utente, dataPrestito, dataRestituzione);

        assertEquals(libro, prestito.getLibro());
        assertEquals(utente, prestito.getUtente());
        assertEquals(dataPrestito, prestito.getDataPrestito());
        assertEquals(dataRestituzione, prestito.getDataRestituzione());
    }

    @Test
    void testSetDataPrestitoERestituzione() {
        Libro libro = new Libro("Il Signore degli Anelli", "Tolkien", 1954, "9876543210123");
        Utente utente = new Utente("Luigi", "Verdi", 2);
        Date data1 = Date.valueOf(LocalDate.now());
        Date data2 = Date.valueOf(LocalDate.now().plusDays(10));

        Prestito prestito = new Prestito(libro, utente, data1, data2);

        Date nuovaDataPrestito = Date.valueOf(LocalDate.now().minusDays(1));
        Date nuovaDataRestituzione = Date.valueOf(LocalDate.now().plusDays(15));

        prestito.setDataPrestito(nuovaDataPrestito);
        prestito.setDataRestituzione(nuovaDataRestituzione);

        assertEquals(nuovaDataPrestito, prestito.getDataPrestito());
        assertEquals(nuovaDataRestituzione, prestito.getDataRestituzione());
    }

    @Test
    void testEqualsAndHashCode() {
        Libro libro = new Libro("1984", "George Orwell", 1949, "1234567890123");
        Utente utente = new Utente("Mario", "Rossi", 1);
        Date oggi = Date.valueOf(LocalDate.now());

        Prestito p1 = new Prestito(libro, utente, oggi, oggi);
        Prestito p2 = new Prestito(libro, utente, oggi, oggi);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testToString() {
        Libro libro = new Libro("Il nome della rosa", "Umberto Eco", 1980, "1122334455667");
        Utente utente = new Utente("Anna", "Bianchi", 3);
        Date oggi = Date.valueOf(LocalDate.now());

        Prestito prestito = new Prestito(libro, utente, oggi, oggi);

        String result = prestito.toString();
        assertTrue(result.contains("utente="));
        assertTrue(result.contains("libro="));
    }
}
