package com.biblioteca.model;

public class Utente {

    // variabili nome, cognome e idUtente
    private static String nome;
    private static String cognome;
    private static int idUtente;

    // costruttore getter e setter
    public Utente (String nome, String cognome, int idUtente){
        this.nome = nome;
        this.cognome = cognome;
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    // metodo per stampare i dettagli dell'utente
    public static void dettagliUtente(){

        System.out.println("Nome: " + nome);
        System.out.println("Cognome: " + cognome);
        System.out.println("ID Utente: " + idUtente);
    }
}