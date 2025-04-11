package com.biblioteca.model;

import com.biblioteca.model.Biblioteca;

import java.util.HashMap;
import java.util.Map;

public class Prestito {

    // Mappa per associare i libri agli utenti (chi ha preso il libro)
    private static Map<Libro, Utente> prestitiInCorso = new HashMap<>();

    // Metodo per trovare l'indice di un libro nell'array della biblioteca
    public static int trovaIndiceLibro(Libro l) {
        for (int i = 0; i < Biblioteca.size; i++) {
            if (Biblioteca.dati[i].equals(l)) {
                return i;  // Restituisce l'indice se il libro è trovato
            }
        }
        return -1;  // Se il libro non viene trovato

        // Metodo per eseguire il prestito (associato con l'utente e libro)
    }
    public static void eseguiPrestito(Libro l, Utente u) {
        int index = trovaIndiceLibro(l);
        // Verifica se il libro esiste nell'array di Biblioteca
        if (index != -1) {

            if (Biblioteca.dati[index] != null) { // Supponiamo che il libro sia disponibile per il prestito
               Biblioteca.rimuovi(index);
                prestitiInCorso.put(l, u);
                System.out.println("Prestito riuscito con sucesso");
            }
        }else{

            // Se il libro non è disponibile, stampa un messaggio
            System.out.println("Imposibbile, il libro " + l.getTitolo() + " è stato già preso in prestito");

        }

    }

    // Metodo per associare un libro a un utente (quando il libro viene preso in prestito)
    public static void associaLibroAUtente(Libro l) {
       if(prestitiInCorso.containsKey(l)){ //se  la chiave libro l esiste
           System.out.println( l.getTitolo()+  " è stato preso in prestito da "  + prestitiInCorso.get(l).getNome());
       }else {
           System.out.println("Questo libro è stato già preso in prestito");
       }


    }

    // Metodo per restituire un libro
    public static boolean restituisciLibro(Libro l, Utente u) {
        // Verifica se il libro è stato preso in prestito
        if (prestitiInCorso.containsKey(l) && prestitiInCorso.get(l).equals(u)) {
            // Restituisci il libro in biblioteca

                Biblioteca.aggiungi(l);  // Rimetti il libro nell'array della biblioteca
                prestitiInCorso.remove(l);   // Rimuovi il prestito dalla mappa
                System.out.println(u.getNome() + " ha restituito " + l.getTitolo());
                return true;
        }if(prestitiInCorso.containsKey(l)&& !prestitiInCorso.get(l).equals(u)) { //se  la chiave libro l esiste
            System.out.println(l.getTitolo() + " è stato  preso in prestito da un altro utente " + prestitiInCorso.get(l).getNome());
        return  false;
        } else {
            System.out.println("Il libro " + l.getTitolo() + " non è stato preso in prestito da nesuuno" );
            return false;
        }
    }



    }
