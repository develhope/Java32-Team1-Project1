package com.biblioteca.utils;

import com.biblioteca.model.Libro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LibroGenerator {

    static Random random = new Random(2);
    static List<String> nomi = List.of("Luigi","Sabrina","Anna");
    static List<String> cognomi = List.of("Bianchi","Gialli","Verdi");
    static List<String> words = List.of(
            "defensive",
            "inspire",
            "debate",
            "replace",
            "crop",
            "circumstance",
            "your",
            "music",
            "gas",
            "free",
            "photograph",
            "snap",
            "poll",
            "which",
            "designer",
            "shot",
            "Mr",
            "learning",
            "beach",
            "sex",
            "widely",
            "chemical",
            "or",
            "decide",
            "no",
            "concern",
            "heat",
            "final",
            "boat",
            "province",
            "active",
            "originally",
            "four",
            "wet",
            "radical",
            "small",
            "organize",
            "shopping",
            "championship",
            "finish",
            "creation",
            "craft",
            "veteran",
            "participate",
            "tree",
            "frequently",
            "figure",
            "reveal",
            "PC",
            "blow",
            "letter",
            "description",
            "affair",
            "peer",
            "industrial",
            "add",
            "fantasy",
            "frustration",
            "bury",
            "cooking",
            "tobacco",
            "incentive",
            "good",
            "test",
            "concentration",
            "contract",
            "with",
            "perception",
            "rough",
            "member",
            "rule",
            "find",
            "mainly",
            "recognize",
            "job",
            "per",
            "gray",
            "generate",
            "sanction",
            "clinical",
            "knee",
            "raw",
            "difficult",
            "approach",
            "read",
            "cheap",
            "trail",
            "limited",
            "tomato",
            "resistance",
            "destroy",
            "voice",
            "founder",
            "spending",
            "mm-hmm");

    public static void main(String[] args) {
        LibroGenerator lg = new LibroGenerator();

        List<Libro> lista = lg.generaLibri(10);
        for(Libro l : lista){
            System.out.println(lg.generaInsert(l));
        }
        //System.out.println(lg.generaLibri(10));
    }

    public List<Libro> generaLibri(int n) {
        List<Libro> libri = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            libri.add(generaLibro());
        }
        return libri;
    }

    public Libro generaLibro() {
        return new Libro(generaTitolo(),
                generaAutore(),generaAnno(),
                generaIsbn(), generaNumeroCopie());
    }

    public String generaAutore() {
        String nome = nomi.get(random.nextInt(nomi.size()));
        String cognome = cognomi.get(random.nextInt(cognomi.size()));

        return nome + " " + cognome;
    }

    public String generaIsbn() {
        return String.valueOf(random.nextLong(100000000000l,999999999999l));
    }

    public int generaAnno() {
        return random.nextInt(1, LocalDate.now().getYear() + 1);
    }

    public String generaTitolo() {

        int numeroParole = random.nextInt(2,5);
        StringBuilder titolo = new StringBuilder();
        for(int i = 0; i<numeroParole; i++){
           String parola = words.get(random.nextInt(words.size()));
           titolo.append(" " + parola);
        }
        return titolo.toString().trim();
    }

    public int generaNumeroCopie() {
        return random.nextInt(0,100);
    }

    public String generaInsert(Libro l){
        return  "INSERT INTO libri (titolo, autore, anno_pubblicazione,isbn, numero_copie) " +
                        "VALUES ( '"+l.getTitolo()+"','" +l.getAutore()+"',"+l.getAnnoPubblicazione()
                            +",'"+l.getISBN()+"'," +l.getNumeroCopie()+");";


    }

//    public String generaInserts(Libro l){
//        return null;
//    }
}
