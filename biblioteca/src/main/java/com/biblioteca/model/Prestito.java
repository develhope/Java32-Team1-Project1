package com.biblioteca.model;
import com.biblioteca.model.Biblioteca;
import java.util.HashMap;
import java.util.Map;

    /**
     * Classe che gestisce i prestiti di libri nella biblioteca.
     * Tiene traccia dei libri prestati associandoli agli utenti tramite una mappa
     * e fornisce funzionalità per eseguire prestiti, verificare associazioni e restituire libri.
     *
    */
public class Prestito {
     /**
     * Mappa che associa i libri prestati agli utenti che li hanno presi in prestito.
     */
    private static Map<Libro, Utente> prestitiInCorso = new HashMap<>();

    /**
     * Trova l'indice di un libro nell'array della biblioteca.
     *
     * @param l Il libro da cercare.
     * @return L'indice del libro nell'array della biblioteca, o -1 se non trovato.
     */
    public static int trovaIndiceLibro(Libro l) {
        for (int i = 0; i < Biblioteca.size; i++) {
            if (Biblioteca.dati[i].equals(l)) {
                return i; // Restituisce l'indice se il libro è trovato
            }
        }
        return -1; // Restituisce -1 se il libro non è trovato
    }

    /**
     * Esegue il prestito di un libro a un utente.
     * Rimuove il libro dalla biblioteca e lo associa all'utente nella mappa dei prestiti.
     *
     * @param l Il libro da prestare.
     * @param u L'utente che prende in prestito il libro.
     */
    public static void eseguiPrestito(Libro l, Utente u) {
        int index = trovaIndiceLibro(l);
        // Verifica se il libro esiste nella biblioteca
        if (index != -1) {
            if (Biblioteca.dati[index] != null) { // Supponiamo che il libro sia disponibile
                Biblioteca.rimuovi(index); // Rimuove il libro dalla biblioteca
                prestitiInCorso.put(l, u); // Associa il libro all'utente
                System.out.println("Prestito eseguito con successo");
            }
        } else {
            // Se il libro non è disponibile, stampa un messaggio
            System.out.println("Impossibile, il libro " + l.getTitolo() + " è già stato preso in prestito");
        }
    }

    /**
     * Verifica e stampa l'associazione di un libro a un utente.
     * Controlla se il libro è attualmente in prestito e ne riporta l'utente associato.
     *
     * @param l Il libro di cui verificare l'associazione.
     */
    public static void associaLibroAUtente(Libro l) {
        if (prestitiInCorso.containsKey(l)) { // Se il libro è in prestito
            System.out.println(l.getTitolo() + " è stato preso in prestito da " + prestitiInCorso.get(l).getNome());
        } else {
            System.out.println("Questo libro non è stato preso in prestito");
        }
    }

    /**
     * Restituisce un libro alla biblioteca.
     * Verifica che il libro sia in prestito e che l'utente sia quello corretto,
     * quindi lo rimuove dalla mappa dei prestiti e lo reinserisce nella biblioteca.
     *
     * @param l Il libro da restituire.
     * @param u L'utente che restituisce il libro.
     * @return {@code true} se la restituzione è avvenuta con successo, {@code false} altrimenti.
     */
    public static boolean restituisciLibro(Libro l, Utente u) {
        // Verifica se il libro è in prestito e associato all'utente corretto
        if (prestitiInCorso.containsKey(l) && prestitiInCorso.get(l).equals(u)) {
            Biblioteca.aggiungi(l); // Rimette il libro nella biblioteca
            prestitiInCorso.remove(l); // Rimuove il prestito dalla mappa
            System.out.println(u.getNome() + " ha restituito " + l.getTitolo());
            return true;
        } else if (prestitiInCorso.containsKey(l) && !prestitiInCorso.get(l).equals(u)) {
            // Se il libro è in prestito a un altro utente
            System.out.println(l.getTitolo() + " è stato preso in prestito da un altro utente: " + prestitiInCorso.get(l).getNome());
            return false;
        } else {
            // Se il libro non è in prestito
            System.out.println("Il libro " + l.getTitolo() + " non è stato preso in prestito da nessuno");
            return false;
        }
    }
}
    

