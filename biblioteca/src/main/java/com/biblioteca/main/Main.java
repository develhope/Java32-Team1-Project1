package com.biblioteca.main;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Utente;
import com.biblioteca.model.Prestito;

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
        Libro libro1 =new Libro("Quello che so di te", "Nadia Terranova", 2025,  9788823521234L);
        Libro libro2 = new Libro("Fratellino" ,"di Ibrahima Balde e Amets",2025,  9788807895678L);
        Libro libro3 = new Libro("Macroeconomia","N. Gregory Manki",2016, 9788880085096L);
        Libro libro4 = new Libro("Il nome della rosa", " Umberto Eco", 1980,  9788845240000L);
        Libro libro5 = new Libro( "Un mondo nuovo", "Liz Braswell" , 2015, 9781788107686L);


        Biblioteca.aggiungi(libro1);
        Biblioteca.aggiungi(libro2);
        Biblioteca.aggiungi(libro3);
        Biblioteca.aggiungi(libro4);
        Biblioteca.aggiungi(libro5);

        Biblioteca.elencoLibri();
        Utente utente1 = new Utente("Mario", "Rossi", 580);
        Utente.dettagliUtente();
        
        Prestito.eseguiPrestito(libro1,utente1);
        Prestito.associaLibroAUtente(libro1);
        Prestito.restituisciLibro(libro1,utente1);
    }
}
