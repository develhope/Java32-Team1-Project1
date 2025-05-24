package com.biblioteca.main;

//import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Utente;
import com.biblioteca.repository.LibroRepository;
import com.biblioteca.repository.PrestitoRepository;
import com.biblioteca.repository.UtenteRepository;
import com.biblioteca.service.BibliotecaService;
import com.biblioteca.service.UtenteService;

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

    static UtenteRepository utenteRepository = new UtenteRepository();
    static LibroRepository libroRepository = new LibroRepository();
    static BibliotecaService bibliotecaService = new BibliotecaService();
    static PrestitoRepository prestitoRepository = new PrestitoRepository();

    /**
     * Punto di ingresso dell'applicazione Biblioteca.
     * Inizializza i libri, li aggiunge alla biblioteca, crea un utente
     * e fornisce un menu interattivo per eseguire operazioni di biblioteca come
     * visualizzare i libri, effettuare prestiti, restituire libri e visualizzare i prestiti.
     *
     * @param args Argomenti della riga di comando (non utilizzati in questa applicazione).
     */
    public static void main(String[] args) throws SQLException {

        /* Scanner per la lettura dell'input dell'utente dalla console. */
        Scanner sc = new Scanner(System.in);

////        /* Utente di esempio per testare la funzionalità di prestito. */
//        Utente ut1 = new Utente("fra", "carp", 897);

        /* Prestito di esempio che associa l'utente e il libro. */
//        Prestito p1 = new Prestito(libro1, ut1);
//        biblioteca.aggiungiPrestito(p1);

//        // Aggiunta dell'utente di esempio alla lista degli utenti della biblioteca
//        biblioteca.listaUtenti.add(ut1);

        /* Flag per controllare il ciclo principale per l'uscita dall'applicazione. */
        boolean uscita = false;

        //controllo dell'id spostato nel metodo autenticazioneUtente()
        Utente utenteCorrente = UtenteService.autenticazioneUtente();

        if (utenteCorrente != null) {
            System.out.println("Accesso effettuato con successo: " + utenteCorrente);
            System.out.println("Ciao, " + utenteCorrente.getNome() + " " + utenteCorrente.getCognome());
        } else {
            System.out.println("Accesso non riuscito.");
        }

        // sc.close(); // facoltativo se lo scanner viene riutilizzato



        /*
         * Ciclo principale dell'applicazione che mostra un menu e processa l'input dell'utente.
         * Il ciclo continua finché l'utente non sceglie di uscire (opzione 0).
         */
        while (!uscita) {
            // Mostra le opzioni del menu
            System.out.println("\n" + "Scegli un'operazione:" + "\n" +
                    "1 - Vedi elenco libri" + "\n" +
                    "2 - Fai un prestito" + "\n" +
                    "3 - Restituisci un libro" + "\n" +
                    "4 - Visualizza i prestiti effettuati" + "\n" +
                    "5 - Aggiungi un libro" + "\n" +
                    "6 - Cerca libro" + "\n" +
                    "0 - Esci");

            /* Scelta dell'utente dal menu, inizializzata a un valore non valido. */
            int scelta = 9;

            // Legge la scelta dell'utente, gestendo input non validi
            try {
                scelta = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Errore: Inserire un numero.");
            }
            sc.nextLine(); // Pulizia del buffer di input

            /*
             * Processa la scelta dell'utente dal menu.
             * Ogni caso corrisponde a un'operazione diversa della biblioteca.
             */


            // Dichiarazione variabili utilizzate nello switch
            Libro libro = null;
//            Prestito prestito = new Prestito(libro, utenteCorrente,);

            switch (scelta) {
                case 1:
                    /* Visualizza l'elenco dei libri nella biblioteca. */
                    System.out.println("Elenco dei libri disponibili: ");
                    bibliotecaService.elencoLibri();
                    break;

                case 2:
                    /* Gestisce il processo di prestito di un libro. */
                    System.out.println("Benvenuto");
                    utenteCorrente.stampaDettagliUtente();

                    System.out.println(prestitoRepository.disponibilitaLibri());


                    // Richiede il titolo del libro finché non viene trovato un libro valido
                    while (libro == null) { // try catch
                        System.out.println("Inserisci il titolo del libro che vuoi prendere in prestito:");
                        bibliotecaService.elencoLibri();
                        String titoloLibro = sc.nextLine();
                        try {
                            libro = libroRepository.findByTitle(titoloLibro);
                        } catch (NullPointerException e) {
                            // Gestisce eventuali eccezioni di puntatore nullo (anche se non tipicamente sollevate qui)
                        }

                        if (libro == null) {
                            System.err.println("Titolo non trovato. Riprova.");
                        }
                    }

//                    try {
//                        biblioteca.aggiungiPrestito(prestito);
//                        System.out.println("Prestito effettuato: " + prestito.getUtente().getNome() +
//                                " ha preso \"" + prestito.getLibro().getTitolo() + "\".");
//                    } catch (IllegalArgumentException e) {
//                        System.err.println("Errore: " + e.getMessage());
//                    }
                    break;

                case 3:
//                    /* Gestisce il processo di restituzione di un libro. */
//                    if (biblioteca.listaPrestiti.isEmpty()) {
//                        System.err.println("Nessun utente ha effettuato prestiti, bisogna prima effettuare un prestito.");
//                        break;
//                    }

                    // Richiede i dettagli dell'utente per verificare l'identità
                    System.out.println("Inserisci l'ID del prestito che deve essere restituito: ");

                    try {
                        int id = sc.nextInt();
                        prestitoRepository.restituisciPrestito(id);
                    } catch (InputMismatchException e) {
                        System.err.println("Errore: ID non valido");
                    }
                    sc.nextLine(); // Pulizia del buffer di input

                    /* Utente da verificare per l'operazione di restituzione. */
//                    Utente utenteDaVerificare = new Utente(nome, cognome, id);

                    // Verifica se l'utente esiste
//                    if (!utenteRepository.findById(idUtente)) {
//                        System.err.println("L'utente non esiste.");
//                        break;
//                    }

                    // Visualizza i prestiti attivi dell'utente
//                    try {
//                        biblioteca.stampaListaPrestiti(biblioteca.listaPrestitiPerUtente(utenteDaVerificare));
//                    } catch (IllegalArgumentException e) {
//                        System.err.println("Errore: " + e.getMessage());
//                    }

                    // Verifica se l'utente ha prestiti attivi
//                    if (id != -1 && biblioteca.listaPrestitiPerUtente(utenteDaVerificare).isEmpty()) {
//                        System.err.println("L'utente non ha eseguito nessun prestito.");
//                        break;
//                    }

                    /* Libro da restituire, inizialmente nullo. */
//                    System.out.println("Dei seguenti libri, inserire il titolo del quale vuoi restituire:");
//                    while (libro == null) {
//                        String titoloLibro = sc.nextLine();
//                        try {
//                            libro = libroRepository.findByTitle(titoloLibro);
//                        } catch (NullPointerException e) {
//                            // Gestisce eventuali eccezioni di puntatore nullo
//                            if (libro == null) {
//                                System.err.println("Titolo non trovato. Riprova.");
//                            }
//                        }
//                    }

                    // Crea e rimuove il prestito
//                    prestito = new Prestito(libro, utenteDaVerificare);
//                    try {
//                        biblioteca.rimuoviPrestito(prestito);
//                        System.out.println("Restituzione effettuata.");
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Errore: " + e.getMessage());
//                    }
                    break;

                case 4:
                    boolean tornaIndietro = true;
                    while (tornaIndietro) {
                        System.out.println("Menù prestito" +
                                "\n1. Visualizza tutti i prestiti" +
                                "\n2. Visualizza i prestiti di un utente specifico" +
                                "\n0. Torna al menù principale");

                        int sceltaPrestito = sc.nextInt();
                        sc.nextLine();

                        switch (sceltaPrestito) {
                            case 1:
                                System.out.println("Ecco la lista intera dei prestiti:");
                                prestitoRepository.findAllPrestiti();
                                break;

                            case 2:
                                System.out.println("Inserisci l'ID dell'utente:");
                                try {
                                    int id = sc.nextInt();
                                    sc.nextLine();
                                    prestitoRepository.findById(id);
                                } catch (InputMismatchException e) {
                                    System.err.println("Errore: ID non valido");
                                    sc.nextLine();
                                }
                                break;

                            case 0:
                                tornaIndietro = false;
                                break;

                            default:
                                System.err.println("Scelta non valida.");
                        }
                    }
                    break;


//                    /* Utente da verificare per la visualizzazione dei prestiti. */
//                    utenteDaVerificare = new Utente(nome, cognome, id);

                // Verifica se l'utente esiste
//                    if (!biblioteca.esisteUtente(utenteDaVerificare)) {
//                        System.err.println("L'utente non esiste.");
//                        break;
//                    }

                // Visualizza i prestiti dell'utente

                // Verifica se l'utente ha prestiti attivi
//                    if (id != -1 && biblioteca.listaPrestitiPerUtente(utenteDaVerificare).isEmpty()) {
//                        System.err.println("L'utente non ha eseguito nessun prestito.");
//                        break;
//                    }

                case 5:
                    Libro libroNuovo = null;

                    while (libroNuovo == null) {// da modificare
                        System.out.println("Inserisci i dati del libro nuovo");

                        System.out.println("\n Inserisci il titolo del libro: ");
                        String titoloLibroNuovo = sc.nextLine();
                        System.out.println("Inserisci l'autore del libro: ");
                        String autoreLibroNuovo = sc.nextLine();
                        System.out.println("Inserisci l'anno di pubblicazione: ");
                        int annoLibroNuovo = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Inserisci l'ISBN del libro: ");
                        String isbnLibroNuovo = sc.nextLine();
                        System.out.println("Inserisci il numero di copie: ");
                        int numeroDiCopie = sc.nextInt();

                        try {
                            libroNuovo = new Libro(titoloLibroNuovo, autoreLibroNuovo, annoLibroNuovo, isbnLibroNuovo, numeroDiCopie);
//                            libroRepository.addNewLibro(libroNuovo); //modificare i parametri
                            System.out.println("Libro aggiunto con successo!");
                        } catch (IllegalArgumentException e) {
                            System.err.println("Dati non validi" + e.getMessage());
                        }

                    }
                    break;

                case 6:
                    System.out.println("Inserisci il titolo del libro che vuoi cercare:");

                    String t = sc.nextLine();

                    System.out.println(libroRepository.findByTitle(t));

                    break;

//                case 7:
//                    UtenteRepository utente = new UtenteRepository();
//                        utente.findById(4);

                case 0:
                    /* Esce dall'applicazione. */
                    uscita = true;
                    System.out.println("Grazie per aver usato la biblioteca.");
                    break;

                default:
                    /* Gestisce una scelta non valida. */
                    System.err.println("Scelta non valida. Riprova.");
                    break;
            }
        }

        /* Chiude lo scanner per liberare le risorse. */
        sc.close();
    }
}