package com.biblioteca.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe che rappresenta una biblioteca per la gestione di un elenco di libri.
 * Consente di aggiungere, rimuovere e visualizzare libri, mantenendo un array fisso
 * di capacità massima pari a 100 libri.
 */

public class Biblioteca {


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
        if (size < dati.length) {
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
                dati[i] = dati[i + 1];
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

        return size;
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



    public boolean isDisponibilita(Libro l) {
        for (Prestito prestito : listaPrestiti) {
            if (prestito.getLibro().equals(l)) {
                return  false;

            }
        }
        return true;
    }
    /**
     * Visualizza l'elenco dei libri presenti nella biblioteca.
     * Se la biblioteca è vuota, stampa un messaggio di errore.
     */
    public void elencoLibri() {

        // Stampa gli elementi
        for (int i = 0; i < size; i++) {
            System.out.println(dati[i] + "è disponibile "+isDisponibilita(dati[i]));
        }

        if (size == 0) {
            System.err.println("Non ci sono libri disponibili");


        }
    }

    public void aggiungiPrestito(Prestito p) {

            if (! isDisponibilita(p.getLibro())) {
                throw new IllegalArgumentException("Il libro \"" + p.getLibro().getTitolo() + "\" è già in prestito.");
            }

        listaPrestiti.add(p);

        System.out.println("Prestito effettuato: " + p.getUtente().getNome() + " ha preso \"" + p.getLibro().getTitolo() + "\".");
    }

    public void rimuoviPrestito(Prestito p) {
        boolean rimosso= listaPrestiti.remove(p);
        if (!rimosso) {
            throw new IllegalArgumentException("Il libro \"" + p.getLibro().getTitolo() + "\" non è in prestito. Impossibile restituirlo.");
        }


        System.out.println("Restituzione effettuata: " + p.getUtente().getNome() + " ha restituito \"" + p.getLibro().getTitolo() + "\".");
    }


    public Libro cercaLibroPerTitolo(String titolo) {
        for (int i = 0; i < size; i++) {
            Libro l = dati[i];
            if (l != null && l.getTitolo().equalsIgnoreCase(titolo)) {
                return l;
            }
        }
        return null;
    }

}






