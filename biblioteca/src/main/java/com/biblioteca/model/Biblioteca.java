package com.biblioteca.model;

import com.biblioteca.main.Configuration;
import com.biblioteca.repository.BibliotecaRepository;
import com.biblioteca.repository.UtenteRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.*;

/**
 * Classe che rappresenta una biblioteca per la gestione di un elenco di libri.
 * Consente di aggiungere, rimuovere e visualizzare libri, mantenendo un array fisso
 * di capacità massima pari a 100 libri. Gestisce inoltre utenti e prestiti.
 */
public class Biblioteca {
    UtenteRepository utenteRepository = new UtenteRepository();

    /**
     * Lista degli utenti registrati nella biblioteca.
     */
    public List<Utente> listaUtenti = new ArrayList<>();

    /**
     * Lista dei prestiti attivi nella biblioteca.
     */
    public List<Prestito> listaPrestiti = new ArrayList<>();

    /**
     * Array di libri memorizzati nella biblioteca.
     * Ha una capacità massima di 100 elementi.
     */
    private final Libro[] dati = new Libro[100];

    /**
     * Numero attuale di libri presenti nella biblioteca.
     */
    private int size = 0;

    /**
     * Rimuove un libro dalla biblioteca in base all'indice specificato.
     *
     * @param index L'indice del libro da rimuovere.
     */
    public void rimuovi(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                dati[i] = dati[i + 1]; // diventerà DELETE
            }
            dati[--size] = null;
        }
    }

    /**
     * Restituisce il numero attuale di libri nella biblioteca.
     *
     * @return Il numero di libri presenti.
     */
    public int dimensione() {
        return size; // SELECT COUNT() FROM LIBRI
    }

    /**
     * Restituisce il libro all'indice specificato.
     *
     * @param index L'indice del libro da recuperare.
     * @return Il libro all'indice specificato, o null se l'indice non è valido.
     */
    public Libro get(int index) {
        return dati[index]; // SELECT * FROM libri WHERE ID = '2' OR titolo = 'titolo libro'
    }

    /**
     * Verifica se un libro è disponibile (non in prestito).
     *
     * @param l Il libro da verificare.
     * @return true se il libro è disponibile, false se è in prestito.
     */
    public boolean isDisponibilita(Libro l) {
        for (Prestito prestito : listaPrestiti) {
            if (prestito.getLibro().equals(l)) {
                return false;
            }
        }
        return true;
    }


    public void elencoUtenti() {

        try {
            List<Utente> listaUtenti = utenteRepository.findUtente();

            for (Utente u : listaUtenti) {
                System.out.println(u);
            }
        } catch (SQLException e) {
            System.err.println("Elenco non trovato " + e);
        }
    }
            /**
             * Aggiunge un prestito alla lista dei prestiti, verificando la disponibilità del libro.
             *
             * @param p Il prestito da aggiungere.
             * @throws IllegalArgumentException se il libro è già in prestito.
             */
            public void aggiungiPrestito (Prestito p){
                if (!isDisponibilita(p.getLibro())) {
                    throw new IllegalArgumentException("Il libro \"" + p.getLibro().getTitolo() + "\" è già in prestito.");
                }
                listaPrestiti.add(p);
            }

            /**
             * Rimuove un prestito dalla lista dei prestiti.
             *
             * @param p Il prestito da rimuovere.
             * @throws IllegalArgumentException se il libro non è in prestito.
             */
            public void rimuoviPrestito (Prestito p){
                boolean trovato = false;

                Iterator<Prestito> iterator = listaPrestiti.iterator();
                while (iterator.hasNext()) {
                    Prestito prestito = iterator.next();
                    if (prestito.equals(p)) {
                        iterator.remove();  // Rimozione sicura durante l'iterazione
                        trovato = true;
                        break;
                    }
                }

                if (!trovato) {
                    throw new IllegalArgumentException("Il libro \"" + p.getLibro().getTitolo() + "\" non è in prestito. Impossibile restituirlo.");
                }
            }

            /**
             * Verifica se un utente esiste nella lista degli utenti registrati.
             *
             * @param utenteVerifica L'utente da verificare.
             * @return true se l'utente esiste, false altrimenti.
             */
            public boolean esisteUtente (Utente utenteVerifica){
                for (Utente utente : listaUtenti) {
                    if (utente.equals(utenteVerifica)) {
                        return true;
                    }
                }
                return false;
            }

            /**
             * Stampa l'elenco dei prestiti specificati.
             *
             * @param list La lista di prestiti da stampare.
             */
            public void stampaListaPrestiti (List < Prestito > list) {
                for (Prestito p : list) {
                    System.out.println(p);
                }
            }

        }