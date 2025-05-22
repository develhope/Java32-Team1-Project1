package com.biblioteca.model;



import java.sql.Date; //oppure import java.util.Date?
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe che rappresenta un prestito tra un utente e un libro.
 */
public class Prestito {
    /** ID del prestito (chiave primaria nel DB). */
    private int idPrestito;

    /** Utente associato al prestito. */
    private final Utente utente;

    /** Libro associato al prestito. */
    private final Libro libro;

    //prima era LocalDateTime ma dava problemi con il tipo nel db
    private LocalDateTime dataPrestito;

    private LocalDateTime dataRestituzione;
    /**
     * Costruttore che crea un prestito, verificando la disponibilità del libro.
     *
     * @param libro Il libro da prendere in prestito.
     * @param utente L'utente che prende in prestito il libro.
     * @throws IllegalArgumentException se il libro non è disponibile.
     */
    public Prestito(Libro libro, Utente utente, LocalDateTime dataPrestito, LocalDateTime dataRestituzione,int idPrestito ) {
        this.utente = utente;
        this.libro = libro;
        this.dataPrestito = dataPrestito;
        this.dataRestituzione = dataRestituzione;
        this.idPrestito=idPrestito;
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
    public Libro getLibro()  {
        return libro;
    }

    // Override di equals e hashCode per confrontare prestiti

    public LocalDateTime getDataPrestito() {
        return dataPrestito;
    }

    public LocalDateTime getDataRestituzione() {
        return dataRestituzione;
    }

    public void setDataPrestito(LocalDateTime dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

    public int getIdPrestito() {
        return idPrestito;
    }

    public void setIdPrestito(int idPrestito) {
        this.idPrestito = idPrestito;
    }

    public void setDataRestituzione(LocalDateTime dataRestituzione) {
        this.dataRestituzione = dataRestituzione;

    }
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