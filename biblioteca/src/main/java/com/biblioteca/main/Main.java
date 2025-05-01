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
        Scanner sc =new Scanner(System.in);
        Libro libro1 = new Libro("Quello che so di te", "Nadia Terranova", 2025,  9788823521234L);
        Libro libro2 = new Libro("Fratellino" ,"Ibrahima Balde e Amets",2025,  9788807895678L);
        Libro libro3 = new Libro("Macroeconomia","N. Gregory Manki",2016, 9788880085096L);
        Libro libro4 = new Libro("Il nome della rosa", "Umberto Eco", 1980,  9788845240000L);
        Libro libro5 = new Libro( "Un mondo nuovo", "Liz Braswell" , 2015, 9781788107686L);

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.aggiungi(libro1);
        biblioteca.aggiungi(libro2);
        biblioteca.aggiungi(libro3);
        biblioteca.aggiungi(libro4);
        biblioteca.aggiungi(libro5);
        System.out.println("Benvenuto,scelga l’operazione che vuoi effettuare digitando il numero corrispondente:\n" +
                "\n" +
                "Digita 1 per vedere l’elenco dei libri  in biblioteca\n" +
                "\n" +
                "Digita 2 per fare un prestito \n" +
                "\n" +
                "Digita 3 per restituire un libro\n" +
                "\n" +
                "Attendo la tua scelta!");
        int sceltaoperazione = sc.nextInt();
         switch (sceltaoperazione){
             case  1-> {
                 System.out.println("Ecco l'elenco dei libri" +"\n" );
                 biblioteca.elencoLibri();

             }
             case 2 -> {
                 System.out.println("Prima di effettuare l'operazione, inserisci i dati richiesti.");
                 sc.nextLine(); // Pulizia del buffer

                 System.out.print("Inserisci il nome: ");
                 String nome = sc.nextLine();

                 System.out.print("Inserisci il cognome: ");
                 String cognome = sc.nextLine();

                 Utente utente1 = new Utente(nome, cognome, 580);
                 System.out.println("Benvenuto");
                 Utente.dettagliUtente();
                 Libro libro = null;
                 while (libro == null) {
                     System.out.print("Inserisci il titolo del libro che vuole prendere in prestito seguenti libri: "+"\n");
                     biblioteca.elencoLibri();
                     String titoloLibro = sc.nextLine();

                     libro = biblioteca.cercaLibroPerTitolo(titoloLibro);
                     if (libro == null) {
                         System.out.println("Libro non trovato. Riprova.");
                     }

                 }Prestito prestito = new Prestito(libro,utente1);
                 try {
                     biblioteca.aggiungiPrestito(prestito);
                 } catch (IllegalArgumentException e) {
                     System.out.println("Errore: " + e.getMessage());
                 }


             }case 3->{

            biblioteca.rimuoviPrestito(prestito);
             }

         }










    }
}