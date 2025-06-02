package com.biblioteca.main;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestito;
import com.biblioteca.model.Utente;
import com.biblioteca.repository.LibroRepository;
import com.biblioteca.repository.PrestitoRepository;
import com.biblioteca.service.BibliotecaService;
import com.biblioteca.service.PrestitoService;
import com.biblioteca.service.UtenteService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NewMain {
    static PrestitoRepository prestitoRepository = new PrestitoRepository();
    static LibroRepository libroRepository = new LibroRepository();
    static BibliotecaService bibliotecaService = new BibliotecaService();
    static PrestitoService prestitoService = new PrestitoService();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        Utente utenteCorrente = UtenteService.autenticazioneUtente();

        if (utenteCorrente != null) {
            System.out.println("Accesso effettuato con successo: " + utenteCorrente);
            System.out.println("Ciao, " + utenteCorrente.getNome() + " " + utenteCorrente.getCognome());
        } else {
            System.out.println("Accesso non riuscito.");
        }

        // Ciclo principale dell'applicazione che mostra un menu e processa l'input dell'utente.
        boolean uscita = false;
        while (!uscita) {
            mostraMenu();
            int scelta = leggiScelta();
            gestisciScelta(scelta, utenteCorrente);
            if (scelta == 0) {
                uscita = true;
            }
            ;
        }

        sc.close();
    }

    public static void mostraMenu() {
        System.out.println("\n" + "Scegli un'operazione:" + "\n" +
                "1 - Vedi elenco libri" + "\n" +
                "2 - Fai un prestito" + "\n" +
                "3 - Restituisci un libro" + "\n" +
                "4 - Visualizza i prestiti effettuati" + "\n" +
                "5 - Aggiungi un libro" + "\n" +
                "6 - Cerca libro" + "\n" +
                "0 - Esci");
    }

    public static int leggiScelta() {
        try {
            int n = sc.nextInt();
            sc.nextLine(); // Pulizia del buffer di input
            return n;
        } catch (InputMismatchException e) {
            System.err.println("Errore: Inserire un numero.");
            return -1;
        }
    }


    public static void gestisciScelta(int scelta, Utente utenteCorrente) throws SQLException {
        switch (scelta) {
            case 1:
                vediElencoLibri();
                break;
            case 2:
                faiPrestito(utenteCorrente);
                break;
            case 3:
                restituisciLibro();
                break;
            case 4:
                visualizzaPrestiti();
                break;
            case 5:
                aggiungiLibro();
                break;
            case 6:
                cercaLibro();
                break;
            case 7:
                break;
            case 0:
                break;
            default:
                System.err.println("Scelta non valida. Riprova.");
        }
    }

    public static void vediElencoLibri() {
        System.out.println("Elenco dei libri disponibili: ");
        bibliotecaService.elencoLibri();
    }

    public static void faiPrestito(Utente utenteCorrente) throws SQLException {
        Libro libro = null;
        System.out.println("Benvenuto");
        utenteCorrente.stampaDettagliUtente();

        // Richiede il titolo del libro finché non viene trovato un libro valido
        while (libro == null) { // try catch
            System.out.println("Inserisci l'ISBN del libro che vuoi prendere in prestito:");
            //bibliotecaService.elencoLibri();
            System.out.println(prestitoRepository.getLibriDisponibili());
            String ISBN = sc.nextLine();
            try {
                libro = libroRepository.findById(ISBN);
            } catch (NullPointerException e) {
                // Gestisce eventuali eccezioni di puntatore nullo (anche se non tipicamente sollevate qui)
            }

//             Nuovo controllo: il libro deve essere tra quelli disponibili
//                        if (libro != null && prestitoRepository.getNumeroCopieDisponibili(libro) > 0) {
//                            System.err.println("Il libro selezionato non è disponibile per il prestito non essendoci copie.");
//                            libro = null;
//                        }

            if (libro == null) {
                System.err.println("Titolo non trovato. Riprova.");
            }
        }

        try {
            Prestito prestito = new Prestito(libro, utenteCorrente, LocalDateTime.now(), null);
            boolean creato = prestitoService.create(prestito);
            if (creato) {
                System.out.println("Prestito effettuato: " +
                        " hai preso \"" + prestito.getLibro().getTitolo() + "\".");
            } else {
                System.out.println("Prestito non effettuato");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }

    public static void restituisciLibro() throws SQLException {
        System.out.println("Inserisci l'ID del prestito che deve essere restituito: ");

        try {
            int id = sc.nextInt();
            prestitoRepository.restituisciPrestito(id);
        } catch (InputMismatchException e) {
            System.err.println("Errore: ID non valido");
        }
        sc.nextLine();
    }

    public static void visualizzaPrestiti() throws SQLException {
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
    }

    public static void aggiungiLibro() throws SQLException {
        try {
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

            Libro libroNuovo = new Libro(titoloLibroNuovo, autoreLibroNuovo, annoLibroNuovo, isbnLibroNuovo, numeroDiCopie);
            libroRepository.addNewLibro(libroNuovo);
            System.out.println("Libro aggiunto con successo!");
        } catch (IllegalArgumentException e) {
            System.err.println("Dati non validi" + e.getMessage());
        }
    }

    public static void cercaLibro() throws SQLException {
        System.out.println("Inserisci il titolo del libro che vuoi cercare:");
        String t = sc.nextLine();
        for(Libro l : libroRepository.findByTitle(t)){
            System.out.println(l);
        }
    }


}
