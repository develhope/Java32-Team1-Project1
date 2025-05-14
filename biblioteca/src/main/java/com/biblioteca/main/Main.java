package com.biblioteca.main;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Utente;
import com.biblioteca.model.Prestito;

import java.util.Scanner;

/**
 * Classe principale per l'applicazione Biblioteca.
 * Questa classe dimostra le funzionalità di base di un sistema di gestione di una biblioteca,
 * inclusa la creazione di libri, l'aggiunta alla biblioteca, la visualizzazione dell'elenco dei libri,
 * la creazione di un utente e l'esecuzione di operazioni di prestito e restituzione.
 */
public class Main {
    /**
     * Punto di ingresso dell'applicazione Biblioteca.
     * Inizializza i libri, li aggiunge alla biblioteca, crea un utente
     * ed esegue operazioni di prestito e restituzione per dimostrare le funzionalità del sistema.
     *
     * @param args Argomenti della riga di comando (non utilizzati in questa applicazione).
     */
    public static void main(String[] args) {






        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        // Aggiunta libri
        biblioteca.aggiungi(new Libro("Quello che so di te", "Nadia Terranova", 2025, 9788823521234L ));
        biblioteca.aggiungi(new Libro("Fratellino", "Ibrahima Balde e Amets", 2025, 9788807895678L));
        biblioteca.aggiungi(new Libro("Macroeconomia", "N. Gregory Manki", 2016, 9788880085096L));
        biblioteca.aggiungi(new Libro("Il nome della rosa", "Umberto Eco", 1980, 9788845240000L));

        Libro libro1=new Libro("Il nome della rosa", "Umberto Eco", 1980, 9788845240000L);
        Utente ut1= new Utente("fra","carp",897);

        boolean uscita = false;
        Utente  utenteCorrente= new Utente("amnistratore","N/D",000);


        while (!uscita) {
            System.out.println("\nScegli un'operazione:"+"\n"+
                                "1 - Vedi elenco libri"+"\n"+
                                "2 - Fai un prestito"+"\n"+
                                "3 - Restituisci un libro"+"\n"+
                                "4 visualizzi i prestiti effetuati"+"\n"+
                                    "0 - Esci");

            int scelta = sc.nextInt();
            sc.nextLine(); // pulizia buffer

            switch (scelta) {
                case 1 -> biblioteca.elencoLibri();

                case 2 -> {

                    System.out.println("Benvenuto");
                    utenteCorrente.stampaDettagliUtente();

                    Libro libro = null;

                    while (libro == null) {
                        System.out.println("Inserisci il titolo del libro che vuoi prendere in prestito:");
                        biblioteca.elencoLibri();
                        String titoloLibro = sc.nextLine();
                            try{
                                libro = biblioteca.cercaLibroPerTitolo(titoloLibro);
                            }catch (NullPointerException e) {
                               // System.err.println("Errore: " + e.getMessage());
                            }


                        if (libro == null) {
                            System.err.println("Titolo non trovato. Riprova.");
                        }
                    }

                    Prestito prestito = new Prestito(libro, utenteCorrente);
                    try {
                        biblioteca.aggiungiPrestito(prestito);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Errore: " + e.getMessage());
                    }
                    System.out.println("Prestito effettuato: " + prestito.getUtente().getNome() + " ha preso \"" + prestito.getLibro().getTitolo() + "\".");
                }

                case 3 -> {
                    if (biblioteca.listaPrestiti.isEmpty()) {
                        System.err.println("Errore: nessun prestito effettuato. Devi prima prendere in prestito un libro.");
                        break;
                    }
                    System.out.print("Prima di effettuare l'operazione, inserisci i dati richiesti "+"\n Conferma nome: ");
                    String nome = sc.nextLine();
                    System.out.print("per proseguire inserisca "+"\n  cognome: ");
                    String cognome = sc.nextLine();
                    System.out.print("per proseguire inserisca "+"\n  id: ");
                    int id = sc.nextInt();
                    Utente utenteDaVerificare= new Utente(nome, cognome, id);
                    try {
                        Prestito prestito = biblioteca.listaPrestitiPerUtenti(utenteDaVerificare);
                        System.out.println("Ecco i prestiti effettuati:\n" + prestito);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Errore: " + e.getMessage());
                    }


                    Libro libro = null;

                    while (libro == null) {
                        System.out.println("dei seguenti libri inserire il titolo del quale  voi restituire");

                        String titoloLibro = sc.nextLine();
                        try{
                            libro = biblioteca.cercaLibroPerTitolo(titoloLibro);
                        }catch (NullPointerException e) {
                            // System.err.println("Errore: " + e.getMessage());
                        }


                        if (libro == null) {
                            System.err.println("Titolo non trovato. Riprova.");
                        }
                    }

                    Prestito prestito = new Prestito(libro, utenteDaVerificare);

                    try {
                        biblioteca.rimuoviPrestito(prestito);

                        System.out.println("Restituzione effettuata");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }
                case 4-> {
                    System.out.print("Prima di effettuare l'operazione, inserisci i dati richiesti "+"\n Conferma nome: ");
                    String nome = sc.nextLine();
                    System.out.print("per proseguire inserisca "+"\n  cognome: ");
                    String cognome = sc.nextLine();
                    System.out.print("per proseguire inserisca "+"\n  id: ");
                    int id = sc.nextInt();
                    Utente utenteDaVerificare= new Utente(nome, cognome, id);
                    try {
                        Prestito prestito = biblioteca.listaPrestitiPerUtenti(utenteDaVerificare);
                        System.out.println("Ecco i prestiti effettuati:\n" + prestito);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Errore: " + e.getMessage());
                    }

                }


                case 0 -> {
                    uscita = true;
                    System.out.println("Grazie per aver usato la biblioteca.");
                }

                default -> System.err.println("Scelta non valida. Riprova.");
            }
        }
    }
}











