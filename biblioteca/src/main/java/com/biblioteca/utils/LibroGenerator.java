package com.biblioteca.utils;

import com.biblioteca.model.Libro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LibroGenerator {

    static Random random = new Random(1);

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
        return new Libro(null,
                null,generaAnno(),
                generaIsbn(), generaNumeroCopie());
    }

    public String generaAutore() {
        return null;
    }

    public String generaIsbn() {
        return String.valueOf(random.nextLong(100000000000l,999999999999l));
    }

    public int generaAnno() {
        return random.nextInt(1, LocalDate.now().getYear() + 1);
    }

    public String generaTitolo() {
        return null;
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
