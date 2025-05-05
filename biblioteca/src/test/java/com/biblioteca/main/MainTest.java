package com.biblioteca.main;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    @Test
    public void testAvvioEElencoLibri() {
        String input = "1\n0\n"; // Visualizza libri e poi esce
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("Quello che so di te"));
        assertTrue(output.contains("Fratellino"));
        assertTrue(output.contains("Macroeconomia"));
        assertTrue(output.contains("Il nome della rosa"));
    }

    @Test
    public void testPrestitoLibroValido() {
        String input = "2\nMario\nRossi\nIl nome della rosa\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Prestito effettuato: Mario Rossi ha preso \"Il nome della rosa\""));
    }

    @Test
    public void testPrestitoLibroNonEsistentePoiValido() {
        String input = "2\nMario\nRossi\nLibro Inesistente\nIl nome della rosa\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String errOutput = errContent.toString();
        String output = outContent.toString();

        assertTrue(errOutput.contains("Titolo non trovato. Riprova."));
        assertTrue(output.contains("Prestito effettuato: Mario Rossi ha preso \"Il nome della rosa\""));
    }

    @Test
    public void testRestituzioneLibro() {
        String input = "2\nMario\nRossi\nIl nome della rosa\n3\nMario\nRossi\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Restituzione effettuata: Mario Rossi ha restituito \"Il nome della rosa\""));
    }

    @Test
    public void testRestituzioneConUtenteErrato() {
        String input = "2\nMario\nRossi\nIl nome della rosa\n3\nLuigi\nVerdi\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String errOutput = errContent.toString();
        assertTrue(errOutput.contains("Utente non corrisponde al prestito registrato."));
    }

    @Test
    public void testRestituzioneSenzaPrestito() {
        String input = "3\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String errOutput = errContent.toString();
        assertTrue(errOutput.contains("Errore: nessun prestito effettuato. Devi prima prendere in prestito un libro."));
    }

    @Test
    public void testSceltaNonValida() {
        String input = "99\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String errOutput = errContent.toString();
        assertTrue(errOutput.contains("Scelta non valida. Riprova."));
    }

    @Test
    public void testEsci() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Grazie per aver usato la biblioteca."));
    }
}
