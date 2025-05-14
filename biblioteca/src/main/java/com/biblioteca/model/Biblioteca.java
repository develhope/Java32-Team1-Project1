package com.biblioteca.model;

import com.biblioteca.main.Configuration;

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

    private static final Configuration c = new Configuration();

    private static final String JDBC_URL = c.getProperties().getProperty("jdbcurl");

    private static final String USERNAME = c.getProperties().getProperty("username");

    private static final String PASSWORD = c.getProperties().getProperty("password");

    Connection connection = null;

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
     * Aggiunge un libro alla biblioteca, se c'è spazio disponibile.
     *
     * @param l Il libro da aggiungere.
     */
    public void aggiungi(Libro l) {
        if (size < dati.length) { // deve diventare una INSERT
            dati[size++] = l;
        }
    }

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
        return dati[index];
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

    /**
     * Visualizza l'elenco dei libri presenti nella biblioteca.
     * Se la biblioteca è vuota, stampa un messaggio di errore.
     */
    public void elencoLibri(Biblioteca biblioteca) {


        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();

            String queryElencoLibri = "SELECT * FROM biblioteca.libri";

            ResultSet resultSet = statement.executeQuery(queryElencoLibri);

            System.out.println("\n");
            while (resultSet.next()) {
                String titolo = resultSet.getString("titolo");
                String autore = resultSet.getString("autore");
                int annoPubblicazione = resultSet.getInt("anno_pubblicazione");
                String isbn = resultSet.getString("isbn");

                Libro libro1 = new Libro(titolo, autore, annoPubblicazione, isbn);
                biblioteca.aggiungi(libro1);


                System.out.println("Titolo: " + titolo + ", Autore: " + autore + ", Anno Pubblicazione: " + annoPubblicazione
                        + " ISBN: " + isbn + " E' disponibile? " + isDisponibilita(libro1));
            }
            }catch(SQLException e){
            System.out.println("Errore: " + e.getMessage());
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

            /**
             * Restituisce la lista dei prestiti associati a un utente specifico.
             *
             * @param utenteVerifica L'utente di cui recuperare i prestiti.
             * @return La lista dei prestiti dell'utente specificato.
             */
            public List<Prestito> listaPrestitiPerUtente (Utente utenteVerifica){
                List<Prestito> prestitiUtente = new ArrayList<>();

                for (Prestito prestito : listaPrestiti) {
                    if (prestito.getUtente().equals(utenteVerifica)) {
                        prestitiUtente.add(prestito);
                    }
                }
                return prestitiUtente;
            }

            /**
             * Cerca un libro nella biblioteca in base al titolo, ignorando maiuscole e minuscole.
             *
             * @param titolo Il titolo del libro da cercare.
             * @return Il libro trovato, o null se nessun libro corrisponde al titolo.
             */
            public Libro cercaLibroPerTitolo (String titolo){
                for (int i = 0; i < size; i++) { // SELECT * FROM Libri WHERE Nome LIKE '%titolo%'......
                    Libro l = dati[i];
                    if (l != null && l.getTitolo().equalsIgnoreCase(titolo)) {
                        return l;
                    }
                }
                return null;
            }
        }