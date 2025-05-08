package com.biblioteca.model;

public class Utente {

    // variabili nome, cognome e idUtente
    private  String nome;
    private  String cognome;
    private  int idUtente;

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
    public void stampaDettagliUtente(){

        System.out.println("Nome: " + nome);
        System.out.println("Cognome: " + cognome);
        System.out.println("ID Utente: " + idUtente);
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", idUtente=" + idUtente +
                '}';
    }
}