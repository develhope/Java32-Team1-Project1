package com.biblioteca.model;

/**
 * Classe che rappresenta un utente della biblioteca.
 * Memorizza informazioni come nome, cognome e identificativo unico dell'utente.
 * Nota: i campi sono dichiarati come static, quindi condivisi tra tutte le istanze della classe.
 */
public class Utente {

    /** Nome dell'utente, condiviso tra tutte le istanze. */
    private static String nome;

    /** Cognome dell'utente, condiviso tra tutte le istanze. */
    private static String cognome;

    /** Identificativo unico dell'utente, condiviso tra tutte le istanze. */
    private static int idUtente;

    /**
     * Costruttore che inizializza un utente con nome, cognome e identificativo.
     * I valori sono assegnati ai campi statici, sovrascrivendo eventuali valori precedenti.
     *
     * @param nome      Il nome dell'utente.
     * @param cognome   Il cognome dell'utente.
     * @param idUtente  L'identificativo unico dell'utente.
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
     * Modifica il valore condiviso tra tutte le istanze.
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
     * Modifica il valore condiviso tra tutte le istanze.
     *
     * @param cognome Il nuovo cognome dell'utente.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce l'identificativo dell'utente.
     *
     * @return L'identificativo dell'utente.
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     * Imposta l'identificativo dell'utente.
     * Modifica il valore condiviso tra tutte le istanze.
     *
     * @param idUtente Il nuovo identificativo dell'utente.
     */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * Stampa i dettagli dell'utente, inclusi nome, cognome e identificativo.
     * Poiché i campi sono statici, restituisce i valori più recenti assegnati.
     */
    public static void dettagliUtente() {
        System.out.println("Nome: " + nome);
        System.out.println("Cognome: " + cognome);
        System.out.println("ID Utente: " + idUtente);
    }
}
