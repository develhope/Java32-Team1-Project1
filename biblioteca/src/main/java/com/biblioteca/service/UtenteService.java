package com.biblioteca.service;

import com.biblioteca.model.Utente;
import com.biblioteca.repository.UtenteRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UtenteService {

    private static UtenteRepository utenteRepository = new UtenteRepository();

    public static Utente autenticazioneUtente(){
        Utente utenteCorrente = null;
        while (utenteCorrente == null) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Benvenuto, inserisca il suo ID:");

            try {

                int idUtente = sc.nextInt();
                utenteCorrente = utenteRepository.findById(idUtente);

                if (utenteCorrente == null) {
                    System.out.println("Utente non trovato. Riprova.");
                }

            } catch (InputMismatchException e) {
                System.err.println("Errore: inserire un numero intero.");
                sc.nextLine(); // consuma l'input errato
            } catch (Exception e) {
                System.err.println("Errore durante la ricerca dell'utente: " + e.getMessage());
                break; // esce dal ciclo in caso di errore grave
            }
        }
        return utenteCorrente;
    }


}
