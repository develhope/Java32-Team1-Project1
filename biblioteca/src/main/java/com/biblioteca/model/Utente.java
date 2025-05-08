package com.biblioteca.model;

import java.util.Objects;

/**
 * Classe che rappresenta un utente della biblioteca.
 * Contiene informazioni come il nome, il cognome e un identificativo unico (ID utente).
 */
public class Utente {

    // variabili nome, cognome e idUtente
    /** Nome dell'utente. */
    private String nome;

    /** Cognome dell'utente. */
    private String cognome;

    /** Identificativo unico dell'utente. */
    private int idUtente;

    // costruttore getter e setter
    /**
     * Costruttore che crea un utente con il nome, cognome e ID specificati.
     *
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param idUtente L'identificativo unico dell'utente.
     */
    public Utente(String nome, String cognome, int idUtente) {
        this.nome = nome;
        this.cognome = cognome;
        this.idUtente = idUtente;
    }

    /**
     * Restituisce il nome dell'utente.
     *
     * @return Il nome dell'utente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param nome Il nuovo nome dell'utente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return Il cognome dell'utente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param cognome Il nuovo cognome dell'utente.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce l'identificativo unico dell'utente.
     *
     * @return L'ID utente.
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     * Imposta l'identificativo unico dell'utente.
     *
     * @param idUtente Il nuovo ID utente.
     */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    // metodo per stampare i dettagli dell'utente
    /**
     * Stampa i dettagli dell'utente (nome, cognome e ID) sulla console.
     */
    public void stampaDettagliUtente() {
        System.out.println("Nome: " + nome);
        System.out.println("Cognome: " + cognome);
        System.out.println("ID Utente: " + idUtente);
    }

    /**
     * Restituisce una rappresentazione testuale dell'utente.
     * Include nome, cognome e ID utente.
     *
     * @return Una stringa che descrive l'utente.
     */
    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", idUtente=" + idUtente +
                '}';
    }

    /**
     * Confronta questo utente con un altro oggetto per verificarne l'uguaglianza.
     * Due utenti sono considerati uguali se hanno lo stesso nome, cognome e ID.
     *
     * @param o L'oggetto con cui confrontare questo utente.
     * @return true se gli utenti hanno gli stessi nome, cognome e ID, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return idUtente == utente.idUtente && Objects.equals(nome, utente.nome) && Objects.equals(cognome, utente.cognome);
    }

    /**
     * Calcola il codice hash dell'utente basato su nome, cognome e ID.
     *
     * @return Il codice hash dell'utente.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, idUtente);
    }
}