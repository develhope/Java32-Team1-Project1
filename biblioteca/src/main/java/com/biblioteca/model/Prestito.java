package com.biblioteca.model;

import java.util.Objects;

/**
 * Classe che rappresenta un prestito tra un utente e un libro.
 */
public class Prestito {
    private final Utente utente;
    private final Libro libro;

    /**
     * Costruttore che crea un prestito, verificando la disponibilità del libro.
     * @throws IllegalArgumentException se il libro non è disponibile.
     */
    public Prestito( Libro libro, Utente utente ) {
        this.utente = utente;
        this.libro = libro;
            System.out.println("Prestito effettuato: " + utente.getNome() + " ha preso \"" + libro.getTitolo() + "\".");

    }

    public Utente getUtente() {
        return utente;
    }

    public Libro getLibro() {
        return libro;
    }

    // Override di equals e hashCode per confrontare prestiti




    @Override
    public String toString() {
        return "Prestito{" +
                "utente=" + utente +
                ", libro=" + libro +
                '}';
    }
}
