package com.biblioteca.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Classe che rappresenta un prestito e ne gestisce l'esecuzione e la restituzione.
 */
public class Prestito {
    private final Utente utente;
    private final Libro libro;

    // Mappa globale dei prestiti attivi: libro → prestito
    private static final Map<Libro, Prestito> prestitiAttivi = new HashMap<>();

    /**
     * Crea un nuovo prestito e lo registra automaticamente, se possibile.
     */
    public Prestito(Utente utente, Libro libro) {
        this.utente = utente;
        this.libro = libro;

        int posizione = trovaLibroInBiblioteca(libro);

        if (posizione == -1) {
            System.out.println(" Il libro \"" + libro.getTitolo() + "\" non è disponibile nella biblioteca.");
        } else if (prestitiAttivi.containsKey(libro)) {
            System.out.println(" Il libro è già in prestito a " + prestitiAttivi.get(libro).utente.getNome());
        } else {
            Biblioteca.rimuovi(posizione);
            prestitiAttivi.put(libro, this);
            System.out.println(" Prestito effettuato: " + utente.getNome() + " ha preso \"" + libro.getTitolo() + "\".");
        }
    }

    /**
     * Restituisce il libro associato a questo prestito.
     */
    public void restituisci() {
        if (prestitiAttivi.containsKey(libro) && prestitiAttivi.get(libro) == this) {
            Biblioteca.aggiungi(libro);
            prestitiAttivi.remove(libro);
            System.out.println( libro.getTitolo() + "\" è stato restituito da " + utente.getNome());
        } else {
            System.out.println(" Il prestito non è attivo o non è valido.");
        }
    }

    /**
     * Verifica lo stato attuale di un libro.
     */
    public static void mostraStatoLibro(Libro libro) {
        if (prestitiAttivi.containsKey(libro)) {
            Prestito p = prestitiAttivi.get(libro);
            System.out.println( libro.getTitolo() + "\" è in prestito a " + p.utente.getNome());
        } else {
            System.out.println( libro.getTitolo() + "\" è disponibile.");
        }
    }

    /**
     * Trova la posizione del libro nella biblioteca.
     */
    private static int trovaLibroInBiblioteca(Libro libro) {
        for (int i = 0; i < Biblioteca.size; i++) {
            if (Biblioteca.dati[i] != null && Biblioteca.dati[i].equals(libro)) {
                return i;
            }
        }
        return -1;
    }

    // ============================
    // Getters e override
    // ============================

    public Utente getUtente() {
        return utente;
    }

    public Libro getLibro() {
        return libro;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "utente=" + utente +
                ", libro=" + libro +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prestito)) return false;
        Prestito that = (Prestito) o;
        return Objects.equals(utente, that.utente) &&
                Objects.equals(libro, that.libro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utente, libro);
    }
}
