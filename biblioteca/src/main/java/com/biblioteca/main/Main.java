package com.biblioteca.main;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Utente;
import com.biblioteca.model.Prestito;

import java.lang.module.Configuration;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe principale per l'applicazione Biblioteca.
 * Questa classe dimostra le funzionalità di base di un sistema di gestione di una biblioteca,
 * inclusa la creazione di libri, l'aggiunta alla biblioteca, la visualizzazione dell'elenco dei libri,
 * la creazione di un utente e l'esecuzione di operazioni di prestito e restituzione.
 */
public class Main {

    private static final Configuration c = new Configuration();

    private static final String JDBC_URL = c.getProperties().getProperty("jdbcurl");

    private static final String USERNAME = c.getProperties().getProperty("username");

    private static final String PASSWORD = c.getProperties().getProperty("password");

    /**
     * Punto di ingresso dell'applicazione Biblioteca.
     * Inizializza i libri, li aggiunge alla biblioteca, crea un utente
     * e fornisce un menu interattivo per eseguire operazioni di biblioteca come
     * visualizzare i libri, effettuare prestiti, restituire libri e visualizzare i prestiti.
     *
     * @param args Argomenti della riga di comando (non utilizzati in questa applicazione).
     */
    public static void main(String[] args) {

        System.out.println(c.getProperties().getProperty("username"));

        Biblioteca biblioteca = new Biblioteca();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database!");

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM biblioteca.libri";

            System.out.println("QUERY UTENTE: " + query);
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("\n");
            while (resultSet.next()) {
                String titolo = resultSet.getString("titolo");
                String autore = resultSet.getString("autore");
                int annoPubblicazione = resultSet.getInt("anno_pubblicazione");
                long isbn = resultSet.getLong("isbn");

                Libro libro1 = new Libro(titolo, autore, annoPubblicazione, isbn);
                biblioteca.aggiungi(libro1);


                System.out.println("Titolo: " + titolo + ", Autore: " + autore + ", Anno Pubblicazione: " + annoPubblicazione
                        + " ISBN: " + isbn);
            }

        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error");
            }
        }
        /** Scanner per la lettura dell'input dell'utente dalla console. */
        Scanner sc = new Scanner(System.in);

        /** Istanza della biblioteca per gestire libri, utenti e prestiti. */
        Biblioteca biblioteca = new Biblioteca();

        // Aggiunta di libri alla biblioteca
        Libro libro1 = new Libro("Quello che so di te", "Nadia Terranova", 2025, 9788823521234L);
        Libro libro2 = new Libro("Fratellino", "Ibrahima Balde e Amets", 2025, 9788807895678L);
        Libro libro3 =new Libro("Macroeconomia", "N. Gregory Manki", 2016, 9788880085096L);
        Libro libro4 =new Libro("Il nome della rosa", "Umberto Eco", 1980, 9788845240000L);
        Libro libro5= new Libro("Il nome della rosa", "Umberto Eco", 1980, 9788845240000L);

        biblioteca.aggiungi(libro1);
        biblioteca.aggiungi(libro2);
        biblioteca.aggiungi(libro3);
        biblioteca.aggiungi(libro4);
        biblioteca.aggiungi(libro5);
        /** Utente di esempio per testare la funzionalità di prestito. */
        Utente ut1 = new Utente("fra", "carp", 897);

        /** Prestito di esempio che associa l'utente e il libro. */
        Prestito p1 = new Prestito(libro1, ut1);
        biblioteca.aggiungiPrestito(p1);

        // Aggiunta dell'utente di esempio alla lista degli utenti della biblioteca
        biblioteca.listaUtenti.add(ut1);

        /** Flag per controllare il ciclo principale per l'uscita dall'applicazione. */
        boolean uscita = false;

        /** Utente corrente, inizializzato come amministratore. */
        Utente utenteCorrente = new Utente("Amministratore", "N/D", 000);
        biblioteca.listaUtenti.add(utenteCorrente);

        /**
         * Ciclo principale dell'applicazione che mostra un menu e processa l'input dell'utente.
         * Il ciclo continua finché l'utente non sceglie di uscire (opzione 0).
         */
        while (!uscita) {
            // Mostra le opzioni del menu
            System.out.println("\nScegli un'operazione:" + "\n" +
                    "1 - Vedi elenco libri" + "\n" +
                    "2 - Fai un prestito" + "\n" +
                    "3 - Restituisci un libro" + "\n" +
                    "4 - Visualizza i prestiti effettuati" + "\n" +
                    "0 - Esci");

            /** Scelta dell'utente dal menu, inizializzata a un valore non valido. */
            int scelta = 9;

            // Legge la scelta dell'utente, gestendo input non validi
            try {
                scelta = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Errore: Inserire un numero.");
            }
            sc.nextLine(); // Pulizia del buffer di input

            /**
             * Processa la scelta dell'utente dal menu.
             * Ogni caso corrisponde a un'operazione diversa della biblioteca.
             */
            switch (scelta) {
                case 1:
                    /** Visualizza l'elenco dei libri nella biblioteca. */
                    biblioteca.elencoLibri();
                    break;

                case 2:
                    /** Gestisce il processo di prestito di un libro. */
                    System.out.println("Benvenuto");
                    utenteCorrente.stampaDettagliUtente();

                    /** Libro da prendere in prestito, inizialmente nullo. */
                    Libro libro = null;

                    // Richiede il titolo del libro finché non viene trovato un libro valido
                    while (libro == null) {
                        System.out.println("Inserisci il titolo del libro che vuoi prendere in prestito:");
                        biblioteca.elencoLibri();
                        String titoloLibro = sc.nextLine();
                        try {
                            libro = biblioteca.cercaLibroPerTitolo(titoloLibro);
                        } catch (NullPointerException e) {
                            // Gestisce eventuali eccezioni di puntatore nullo (anche se non tipicamente sollevate qui)
                        }

                        if (libro == null) {
                            System.err.println("Titolo non trovato. Riprova.");
                        }
                    }

                    // Crea e aggiunge il prestito
                    Prestito prestito = new Prestito(libro, utenteCorrente);
                    try {
                        biblioteca.aggiungiPrestito(prestito);
                        System.out.println("Prestito effettuato: " + prestito.getUtente().getNome() +
                                " ha preso \"" + prestito.getLibro().getTitolo() + "\".");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Errore: " + e.getMessage());
                    }
                    break;

                case 3:
                    /** Gestisce il processo di restituzione di un libro. */
                    if (biblioteca.listaPrestiti.isEmpty()) {
                        System.err.println("Nessun utente ha effettuato prestiti, bisogna prima effettuare un prestito.");
                        break;
                    }

                    // Richiede i dettagli dell'utente per verificare l'identità
                    System.out.print("Prima di effettuare l'operazione, inserisci i dati richiesti " +
                            "\nConferma nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Per proseguire inserisca " + "\ncognome: ");
                    String cognome = sc.nextLine();
                    System.out.print("Per proseguire inserisca " + "\nid: ");
                    int id = -1;
                    try {
                        id = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println(); // Gestisce silenziosamente l'input non valido
                    }
                    sc.nextLine(); // Pulizia del buffer di input

                    /** Utente da verificare per l'operazione di restituzione. */
                    Utente utenteDaVerificare = new Utente(nome, cognome, id);

                    // Verifica se l'utente esiste
                    if (!biblioteca.esisteUtente(utenteDaVerificare)) {
                        System.err.println("L'utente non esiste.");
                        break;
                    }

                    // Visualizza i prestiti attivi dell'utente
                    try {
                        biblioteca.stampaListaPrestiti(biblioteca.listaPrestitiPerUtente(utenteDaVerificare));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Errore: " + e.getMessage());
                    }

                    // Verifica se l'utente ha prestiti attivi
                    if (id != -1 && biblioteca.listaPrestitiPerUtente(utenteDaVerificare).isEmpty()) {
                        System.err.println("L'utente non ha eseguito nessun prestito.");
                        break;
                    }

                    /** Libro da restituire, inizialmente nullo. */
                    libro = null;
                    System.out.println("Dei seguenti libri, inserire il titolo del quale vuoi restituire:");
                    while (libro == null) {
                        String titoloLibro = sc.nextLine();
                        try {
                            libro = biblioteca.cercaLibroPerTitolo(titoloLibro);
                        } catch (NullPointerException e) {
                            // Gestisce eventuali eccezioni di puntatore nullo
                            if (libro == null) {
                                System.err.println("Titolo non trovato. Riprova.");
                            }
                        }
                    }

                    // Crea e rimuove il prestito
                    prestito = new Prestito(libro, utenteDaVerificare);
                    try {
                        biblioteca.rimuoviPrestito(prestito);
                        System.out.println("Restituzione effettuata.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 4:
                    /** Visualizza i prestiti effettuati da un utente specifico. */
                    System.out.print("Prima di effettuare l'operazione, inserisci i dati richiesti " +
                            "\nConferma nome: ");
                    nome = sc.nextLine();
                    System.out.print("Per proseguire inserisca " + "\ncognome: ");
                    cognome = sc.nextLine();
                    System.out.print("Per proseguire inserisca " + "\nid: ");
                    id = -1;
                    try {
                        id = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println(); // Gestisce silenziosamente l'input non valido
                    }
                    sc.nextLine(); // Pulizia del buffer di input

                    /** Utente da verificare per la visualizzazione dei prestiti. */
                    utenteDaVerificare = new Utente(nome, cognome, id);

                    // Verifica se l'utente esiste
                    if (!biblioteca.esisteUtente(utenteDaVerificare)) {
                        System.err.println("L'utente non esiste.");
                        break;
                    }

                    // Visualizza i prestiti dell'utente
                    try {
                        biblioteca.stampaListaPrestiti(biblioteca.listaPrestitiPerUtente(utenteDaVerificare));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Errore: " + e.getMessage());
                    }

                    // Verifica se l'utente ha prestiti attivi
                    if (id != -1 && biblioteca.listaPrestitiPerUtente(utenteDaVerificare).isEmpty()) {
                        System.err.println("L'utente non ha eseguito nessun prestito.");
                        break;
                    }
                    break;

                case 0:
                    /** Esce dall'applicazione. */
                    uscita = true;
                    System.out.println("Grazie per aver usato la biblioteca.");
                    break;

                default:
                    /** Gestisce una scelta non valida. */
                    System.err.println("Scelta non valida. Riprova.");
                    break;
            }
        }

        /** Chiude lo scanner per liberare le risorse. */
        sc.close();
    }
}