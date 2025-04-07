package com.biblioteca.model;

public class Biblioteca {
    public static Libro[] dati = new Libro[100];
    public static int size = 0;


    public static void aggiungi(Libro l) {
        if (size < dati.length) {
            dati[size++] = l;
        }
    }

    public static void rimuovi(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                dati[i] = dati[i + 1];
            }
            dati[--size] = null;
        }
    }


    public int dimensione() {

        return size;
    }

    public Libro get(int index) {
        return dati[index];
    }

    public static void elencoLibri() {

        // Stampa gli elementi
        for (int i = 0; i < size; i++) {
            System.out.println(dati[i]);
        }

        if (size == 0){
            System.err.println("Non ci sono libri disponibili");
   

}

