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
        biblioteca.aggiungi(new Libro("Quello che so di te", "Nadia Terranova", 2025, 9788823521234L));
        biblioteca.aggiungi(new Libro("Fratellino", "Ibrahima Balde e Amets", 2025, 9788807895678L));
        biblioteca.aggiungi(new Libro("Macroeconomia", "N. Gregory Manki", 2016, 9788880085096L));
        biblioteca.aggiungi(new Libro("Il nome della rosa", "Umberto Eco", 1980, 9788845240000L));
        biblioteca.aggiungi(new Libro("Un mondo nuovo", "Liz Braswell", 2015, 9781788107686L));

        boolean uscita = false;
        boolean haFattoPrestito = false;
        Utente utenteCorrente = null;
        Libro libroInPrestito = null;

        while (!uscita) {
            System.out.println("\nScegli un'operazione:");
            System.out.println("1 - Vedi elenco libri");
            System.out.println("2 - Fai un prestito");
            System.out.println("3 - Restituisci un libro");
            System.out.println("0 - Esci");

            int scelta = sc.nextInt();
            sc.nextLine(); // pulizia buffer

            switch (scelta) {
                case 1 -> biblioteca.elencoLibri();

                case 2 -> {
                    System.out.print("Inserisci nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Inserisci cognome: ");
                    String cognome = sc.nextLine();

                    utenteCorrente = new Utente(nome, cognome, 1);
                    utenteCorrente.stampaDettagliUtente();

                    biblioteca.elencoLibri();
                    System.out.print("Inserisci il titolo del libro da prendere in prestito: ");
                    String titolo = sc.nextLine();

                    libroInPrestito = biblioteca.cercaLibroPerTitolo(titolo);

                    if (libroInPrestito != null) {
                        Prestito prestito = new Prestito(libroInPrestito, utenteCorrente);
                        try {
                            biblioteca.aggiungiPrestito(prestito);
                            haFattoPrestito = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Errore: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Libro non trovato.");
                    }
                }

                case 3 -> {
                    if (!haFattoPrestito) {
                        System.out.println("Errore: nessun prestito effettuato. Devi prima prendere in prestito un libro.");
                        break;
                    }
                    System.out.print("Conferma nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Conferma cognome: ");
                    String cognome = sc.nextLine();

                    if (!utenteCorrente.getNome().equalsIgnoreCase(nome) || !utenteCorrente.getCognome().equalsIgnoreCase(cognome)) {
                        System.out.println("Utente non corrisponde al prestito registrato.");
                        break;
                    }

                    Prestito prestito = new Prestito(libroInPrestito, utenteCorrente);
                    try {
                        biblioteca.rimuoviPrestito(prestito);
                        haFattoPrestito = false;
                        libroInPrestito = null;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }

                case 0 -> {
                    uscita = true;
                    System.out.println("Grazie per aver usato la biblioteca.");
                }

                default -> System.out.println("Scelta non valida. Riprova.");
            }
        }
    }
}











