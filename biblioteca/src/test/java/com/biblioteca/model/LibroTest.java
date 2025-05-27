package com.biblioteca.model;

/**
 * Classe di test per la verifica delle funzionalità della classe {@link Libro}.
 * Contiene test per il costruttore di default, il costruttore parametrizzato e il metodo {@code equals}.
 * I risultati attesi e ottenuti vengono stampati a console per un confronto manuale.
 */
public class LibroTest {

    /**
     * Metodo principale che esegue una serie di test per verificare il comportamento della classe {@link Libro}.
     * I test includono:
     * <ul>
     *     <li>Verifica del costruttore di default</li>
     *     <li>Verifica del costruttore parametrizzato</li>
     *     <li>Verifica del metodo {@code equals} per libri con lo stesso ISBN</li>
     *     <li>Verifica del metodo {@code equals} per libri con ISBN diverso</li>
     *     <li>Verifica del metodo {@code equals} con confronto a {@code null}</li>
     *     <li>Verifica del metodo {@code equals} con lo stesso oggetto</li>
     * </ul>
     * I risultati dei test vengono stampati a console per consentire una verifica visiva.
     *
     * @param args Argomenti della riga di comando (non utilizzati).
     */
    public static void main(String[] args) {
        // Test 1: Verifica del costruttore di default
        Libro libroDefault = new Libro();
        System.out.println("Test 1 - Costruttore di default:");
        System.out.println("Atteso: titolo='titolo', autore='sconoscuto', annoPubblicazione=0, ISBN='0000000000000000', numeroCopie=0");
        System.out.println("Ottenuto: " + libroDefault.toString());
        System.out.println();

        // Test 2: Verifica del costruttore parametrizzato
        Libro libroParam = new Libro("Il Nome del Vento", "Patrick Rothfuss", 2007, "9788804681830", 5);
        System.out.println("Test 2 - Costruttore parametrizzato:");
        System.out.println("Atteso: titolo='Il Nome del Vento', autore='Patrick Rothfuss', annoPubblicazione=2007, ISBN='9788804681830', numeroCopie=5");
        System.out.println("Ottenuto: " + libroParam.toString());
        System.out.println();

        // Test 3: Verifica del metodo equals() - Libri con stesso ISBN
        Libro libro1 = new Libro("Libro A", "Autore A", 2020, "1234567890123", 3);
        Libro libro2 = new Libro("Libro B", "Autore B", 2021, "1234567890123", 2);
        System.out.println("Test 3 - Equals (stesso ISBN):");
        System.out.println("Atteso: true");
        System.out.println("Ottenuto: " + libro1.equals(libro2));
        System.out.println();

        // Test 4: Verifica del metodo equals() - Libri con ISBN diverso
        Libro libro3 = new Libro("Libro C", "Autore C", 2019, "9876543210987", 1);
        System.out.println("Test 4 - Equals (ISBN diverso):");
        System.out.println("Atteso: false");
        System.out.println("Ottenuto: " + libro1.equals(libro3));
        System.out.println();

        // Test 5: Verifica del metodo equals() - Confronto con null
        System.out.println("Test 5 - Equals (confronto con null):");
        System.out.println("Atteso: false");
        System.out.println("Ottenuto: " + libro1.equals(null));
        System.out.println();

        // Test 6: Verifica del metodo equals() - Stesso oggetto
        System.out.println("Test 6 - Equals (stesso oggetto):");
        System.out.println("Atteso: true");
        System.out.println("Ottenuto: " + libro1.equals(libro1));
    }
}