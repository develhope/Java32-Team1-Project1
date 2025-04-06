package com.biblioteca.model;


// Classe che rappresenta un libro nella biblioteca
public class Libro {
    private String titolo;
    private String autore;
    private int annoPubblicazione;
    private long ISBN;

    // Costruttore di default
    public Libro() {
        titolo= "titolo";
        autore= "sconoscuto";
        annoPubblicazione= 0000;
        ISBN=0000000000000000L;
    }

    // Costruttore parametrizzato
    public Libro(String titolo, String autore, int annoPubblicazione, long  ISBN) {
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.ISBN = ISBN;
    }

    // Getter e Setter
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long  ISBN) {
        this.ISBN = ISBN;
    }

    // Metodo toString per stampare i dettagli del libro in modo leggibile
    @Override
    public String toString() {
        return "Libro{" +
                "Titolo='" + titolo + '\'' +
                ", Autore='" + autore + '\'' +
                ", Anno di Pubblicazione=" + annoPubblicazione +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}