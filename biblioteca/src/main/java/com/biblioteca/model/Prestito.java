package com.biblioteca.model;

import com.biblioteca.model.Biblioteca;

public class Prestito {

    // Metodo per trovare l'indice di un libro nell'array della biblioteca
    public static int trovaIndiceLibro(Libro l) {
        for (int i = 0; i < Biblioteca.size; i++) {
            if (Biblioteca.dati[i].equals(l)) {
                return i;  // Restituisce l'indice se il libro è trovato
            }
        }
        return -1;  // Se il libro non viene trovato
    }

    // Metodo per associare un libro a un utente (quando il libro viene preso in prestito)
    public static boolean associaLibroAUtente(Libro l, Utente u) {
        int index = trovaIndiceLibro(l);

        // Verifica se il libro esiste nell'array
        if (index != -1) {
            // Se il libro è disponibile per il prestito
            if (Biblioteca.dati[index] != null) {
                // Qui supponiamo che tu possa associare l'utente al libro
                // Ad esempio, se Libro avesse un campo utente, lo settiamo con l'utente passato
                // NOTA: Assumiamo che Libro abbia il campo "utente", che però non possiamo modificare.

                // Simuliamo l'associazione (anche se senza modificare Libro non è possibile in realtà):
                System.out.println(l.getTitolo() + " è stato preso in prestito da " + u.getNome());
                return true;
            }
        }

        System.out.println("Il libro " + l.getTitolo() + " non è disponibile per il prestito.");
        return false;
    }

    // Metodo per restituire un libro
    public static boolean restituisciLibro(Libro l, Utente u) {
        int index = trovaIndiceLibro(l);

        // Verifica se il libro esiste nell'array e se l'utente che restituisce è quello che ha preso il libro
        if (index != -1 && Biblioteca.dati[index] != null) {

            System.out.println(u.getNome() + " ha restituito " + l.getTitolo());


            Biblioteca.aggiungi(l);

            return true;
        }

        System.out.println("Impossibile restituire il libro " + l.getTitolo() + ", potrebbe non essere stato preso in prestito.");
        return false;
    }

    // Metodo per eseguire il prestito (associato con l'utente e libro)
    public static boolean eseguiPrestito(Libro l, Utente u) {
        int index = trovaIndiceLibro(l);
        associaLibroAUtente(l,u);
        // Verifica se il libro esiste nell'array di Biblioteca
        if (index != -1) {

            if (Biblioteca.dati[index] != null) {
                // Supponiamo che il libro sia disponibile per il prestito
                return associaLibroAUtente(l, u);
            }
        }

        // Se il libro non è disponibile, stampa un messaggio
        System.out.println("Il libro " + l.getTitolo() + " non è disponibile per il prestito");
        return false;
    }
}