package com.biblioteca.model;

import java.util.Objects;

/**
 * Classe che rappresenta un prestito tra un utente e un libro.
 */
public class Prestito {
    private final Utente utente;
    private final Libro libro;
    Biblioteca biblioteca = new Biblioteca();

    /**
     * Costruttore che crea un prestito, verificando la disponibilità del libro.
     * @throws IllegalArgumentException se il libro non è disponibile.
     */
    public Prestito(Utente utente, Libro libro) {
        this.utente = utente;
        this.libro = libro;

        try {
            int posizione = trovaLibroInBiblioteca(libro);

            if (posizione == -1) {
                throw new IllegalArgumentException("Il libro \"" + libro.getTitolo() + "\" non è disponibile nella biblioteca.");
            }



           biblioteca.aggiungiPrestito(libro);
        biblioteca.rimuovi(posizione);
            System.out.println("Prestito effettuato: " + utente.getNome() + " ha preso \"" + libro.getTitolo() + "\".");

        } catch (IllegalArgumentException e) {
            System.out.println("Errore nel costruttore di Prestito: " + e.getMessage());
            // eventualmente: rethrow se vuoi bloccare comunque
            // throw e;
        }
    }

    /**
     * Restituisce il libro associato a questo prestito alla biblioteca.
     */
    public void restituisci() {
        biblioteca.aggiungi(libro);
        System.out.println("\"" + libro.getTitolo() + "\" è stato restituito da " + utente.getNome());
    }

    /**
     * Trova la posizione del libro nella biblioteca.
     * Metodo temporaneo (meglio delegare alla classe Biblioteca).
     */
    private  int trovaLibroInBiblioteca(Libro libro) {
        for (int i = 0; i < biblioteca.size; i++) {
            if (biblioteca.dati[i] != null && biblioteca.dati[i].equals(libro)) {
                return i;
            }
        }
        return -1;
    }
    public void mostraStatoLibro(Libro libro) {
        if (trovaLibroInBiblioteca(libro) == -1) {
            System.out.println("Il libro \"" + libro.getTitolo() + "\" è in prestito.");
        } else {
            System.out.println("Il libro \"" + libro.getTitolo() + "\" è disponibile.");
        }
    }
    // Getters

    public Utente getUtente() {
        return utente;
    }

    public Libro getLibro() {
        return libro;
    }

    // Override di equals e hashCode per confrontare prestiti

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

    @Override
    public String toString() {
        return "Prestito{" +
                "utente=" + utente +
                ", libro=" + libro +
                '}';
    }
}
