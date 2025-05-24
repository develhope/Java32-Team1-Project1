package com.biblioteca.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe che rappresenta un prestito tra un utente e un libro.
 */
public class Prestito {
    /** ID del prestito (chiave primaria nel DB). */
    private Integer idPrestito;

    /** Utente associato al prestito. */
    private final Utente utente;

    /** Libro associato al prestito. */
    private final Libro libro;

    private LocalDateTime dataPrestito;
    private LocalDateTime dataRestituzione;

    /**
     * Costruttore con ID (utile per caricare da DB).
     */
    public Prestito(Integer idPrestito, Libro libro, Utente utente, LocalDateTime dataPrestito, LocalDateTime dataRestituzione) {
        this.idPrestito = idPrestito;
        this.utente = utente;
        this.libro = libro;
        this.dataPrestito = dataPrestito;
        this.dataRestituzione = dataRestituzione;
    }

    /**
     * Costruttore senza ID (utile per creare nuovo prestito prima di salvarlo nel DB).
     */
    public Prestito(Libro libro, Utente utente, LocalDateTime dataPrestito, LocalDateTime dataRestituzione) {
        this(null, libro, utente, dataPrestito, dataRestituzione); // idPrestito = 0 come placeholder
    }

    public Integer getIdPrestito() {
        return idPrestito;
    }

    public void setIdPrestito(int idPrestito) {
        this.idPrestito = idPrestito;
    }

    public Utente getUtente() {
        return utente;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDateTime getDataPrestito() {
        return dataPrestito;
    }

    public LocalDateTime getDataRestituzione() {
        return dataRestituzione;
    }

    public void setDataPrestito(LocalDateTime dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

    public void setDataRestituzione(LocalDateTime dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "idPrestito=" + idPrestito +
                ", utente=" + utente +
                ", libro=" + libro +
                ", dataPrestito=" + dataPrestito +
                ", dataRestituzione=" + dataRestituzione +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Prestito other = (Prestito) obj;
        return Objects.equals(libro, other.libro) &&
                Objects.equals(utente, other.utente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libro, utente);
    }
}
