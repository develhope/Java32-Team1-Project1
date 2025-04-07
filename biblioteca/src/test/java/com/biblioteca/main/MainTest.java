package com.biblioteca.main;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Utente;
import com.biblioteca.model.Prestito;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUp() {
        // Resetta lo stato della biblioteca
        Biblioteca.size = 0;
        for (int i = 0; i < Biblioteca.dati.length; i++) {
            Biblioteca.dati[i] = null;
        }
        // Reindirizza System.out per catturare l'output
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMainExecution() {
        // Arrange: Simula l'esecuzione del main
        Libro libro1 = new Libro("Quello che so di te", "Nadia Terranova", 2025, 9788823521234L);
        Libro libro2 = new Libro("Fratellino", "di Ibrahima Balde e Amets", 2025, 9788807895678L);
        Libro libro3 = new Libro("Macroeconomia", "N. Gregory Manki", 2016, 9788880085096L);
        Libro libro4 = new Libro("Il nome della rosa", " Umberto Eco", 1980, 9788845240000L);
        Libro libro5 = new Libro("Un mondo nuovo", "Liz Braswell", 2015, 9781788107686L);

        Utente utente1 = new Utente("Mario", "Rossi", 580);


        // Act: Esegui le operazioni del main
        Biblioteca.aggiungi(libro1);
        Biblioteca.aggiungi(libro2);
        Biblioteca.aggiungi(libro3);
        Biblioteca.aggiungi(libro4);
        Biblioteca.aggiungi(libro5);

        Biblioteca.elencoLibri();
        Utente.dettagliUtente();
        Prestito.eseguiPrestito(libro1, utente1);
        Prestito.restituisciLibro(libro1, utente1);

        // Assert: Verifica lo stato della biblioteca e l'output
        assertEquals(5, new Biblioteca().dimensione(), "La biblioteca dovrebbe contenere 5 libri dopo tutte le operazioni");

        String output = outContent.toString();
        // Verifica che l'output contenga le parti attese
        assertTrue(output.contains("Quello chQe so di te"), "L'output dovrebbe elencare il primo libro");
        assertTrue(output.contains("Fratellino"), "L'output dovrebbe elencare il secondo libro");
        assertTrue(output.contains("Macroegitconomia"), "L'output dovrebbe elencare il terzo libro");
        assertTrue(output.contains("Il nome della rosa"), "L'output dovrebbe elencare il quarto libro");
        assertTrue(output.contains("Un mondo nuovo"), "L'output dovrebbe elencare il quinto libro");
        assertTrue(output.contains("Nome: Mario Cognome: Rossi ID Utente: 580"), "L'output dovrebbe includere i dettagli dell'utente");
        assertTrue(output.contains("Quello che so di te è stato preso in prestito da Mario"), "L'output dovrebbe confermare il prestito");
        assertTrue(output.contains("Mario ha restituito Quello che so di te"), "L'output dovrebbe confermare la restituzione");
    }

    @Test
    public void testMainConBibliotecaVuotaIniziale() {
        // Arrange: Esegui solo elencoLibri su biblioteca vuota
        Biblioteca.elencoLibri();

        // Assert
        String output = outContent.toString();
        assertTrue(output.contains("Non ci sono libri disponibili"), "L'output dovrebbe indicare che la biblioteca è vuota");
        assertEquals(0, new Biblioteca().dimensione(), "La dimensione dovrebbe essere 0");
    }

    @AfterEach
    public void tearDown() {
        // Ripristina System.out
        System.setOut(originalOut);
    }
}