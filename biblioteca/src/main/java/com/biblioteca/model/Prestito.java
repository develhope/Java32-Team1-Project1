package com.biblioteca.model;

import java.util.Objects;

/**
 * Classe che rappresenta un prestito tra un utente e un libro.
 */
public class Prestito {
    /** Utente associato al prestito. */
    private final Utente utente;

    /** Libro associato al prestito. */
    private final Libro libro;

    /**
     * Costruttore che crea un prestito, verificando la disponibilità del libro.
     *
     * @param libro Il libro da prendere in prestito.
     * @param utente L'utente che prende in prestito il libro.
     * @throws IllegalArgumentException se il libro non è disponibile.
     */
    public Prestito(Libro libro, Utente utente) {
        this.utente = utente;
        this.libro = libro;
    }

    /**
     * Restituisce l'utente associato al prestito.
     *
     * @return L'utente che ha effettuato il prestito.
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Restituisce il libro associato al prestito.
     *
     * @return Il libro preso in prestito.
     */
    public Libro getLibro() {
        return libro;
    }

    // Override di equals e hashCode per confrontare prestiti

    /**
     * Restituisce una rappresentazione testuale del prestito.
     * Include informazioni sull'utente e sul libro associati al prestito.
     *
     * @return Una stringa che descrive il prestito.
     */
    @Override
    public String toString() {
        return "Prestito{" +
                "utente=" + utente +
                ", libro=" + libro +
                '}';
    }

    /**
     * Confronta questo prestito con un altro oggetto per verificarne l'uguaglianza.
     * Due prestiti sono considerati uguali se hanno lo stesso utente e lo stesso libro.
     *
     * @param obj L'oggetto con cui confrontare questo prestito.
     * @return true se i prestiti hanno lo stesso utente e libro, false altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // stesso riferimento
        if (obj == null || getClass() != obj.getClass()) return false;

        Prestito other = (Prestito) obj;
        return Objects.equals(libro, other.libro) &&
                Objects.equals(utente, other.utente);
    }

    /**
     * Calcola il codice hash del prestito basato sull'utente e sul libro.
     *
     * @return Il codice hash del prestito.
     */
    @Override
    public int hashCode() {
        return Objects.hash(libro, utente);
    }
}