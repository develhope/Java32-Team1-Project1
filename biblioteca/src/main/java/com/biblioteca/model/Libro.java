package com.biblioteca.model;

/**
 * Classe che rappresenta un libro nella biblioteca.
 * Contiene informazioni come il titolo, l'autore, l'anno di pubblicazione e l'ISBN.
 *
*/


// Classe che rappresenta un libro nella biblioteca
public class Libro {

    private boolean disponibilita=true;

    /** Titolo del libro. */
    private String titolo;
    
    /** Autore del libro. */
    private String autore;
    
    /** Anno di pubblicazione del libro. */
    private int annoPubblicazione;
    
     /** Numero ISBN del libro. */
    private long ISBN;

    /**
     * Costruttore di default.
     * Inizializza un libro con valori predefiniti: titolo "titolo", autore "sconoscuto",
     * anno di pubblicazione 0000 e ISBN 0000000000000000.
     */
    public Libro() {
        titolo= "titolo";
        autore= "sconoscuto";
        annoPubblicazione= 0000;
        ISBN=0000000000000000L;

    }

    /**
     * Costruttore parametrizzato.
     * Crea un libro con i valori specificati per titolo, autore, anno di pubblicazione e ISBN.
     *
     * @param titolo            Il titolo del libro.
     * @param autore            L'autore del libro.
     * @param annoPubblicazione L'anno di pubblicazione del libro.
     * @param ISBN              Il numero ISBN del libro.
     */
    public Libro(String titolo, String autore, int annoPubblicazione, long  ISBN) {
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;

        this.ISBN = ISBN;
    }

    public boolean isDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    /**
     * Restituisce il titolo del libro.
     *
     * @return Il titolo del libro.
     */
    public String getTitolo() {
        return titolo;
    }
    
    /**
     * Imposta il titolo del libro.
     *
     * @param titolo Il nuovo titolo del libro.
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    
     /**
     * Restituisce l'autore del libro.
     *
     * @return L'autore del libro.
     */
    public String getAutore() {
        return autore;
    }
    
    /**
     * Imposta l'autore del libro.
     *
     * @param autore Il nuovo autore del libro.
     */
    public void setAutore(String autore) {
        this.autore = autore;
    }

    /**
     * Restituisce l'anno di pubblicazione del libro.
     *
     * @return L'anno di pubblicazione del libro.
     */
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    /**
     * Imposta l'anno di pubblicazione del libro.
     *
     * @param annoPubblicazione Il nuovo anno di pubblicazione del libro.
     */
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    /**
     * Restituisce l'ISBN del libro.
     *
     * @return L'ISBN del libro.
     */
    public long getISBN() {
        return ISBN;
    }

    /**
     * Imposta l'ISBN del libro.
     *
     * @param ISBN Il nuovo ISBN del libro.
     */
    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Restituisce una rappresentazione testuale del libro.
     * Include titolo, autore, anno di pubblicazione e ISBN.
     *
     * @return Una stringa che descrive il libro.
     */
    @Override
    public String toString() {
        return "Libro{" +
                "titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", ISBN=" + ISBN +'\'' +
                "è disponibile "+ disponibilita+

                '}';
    }
}

