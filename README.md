# Java32-Team1-Project
Progetto Gestione Libri - README
Descrizione
Questo progetto Java simula la gestione di una collezione di libri in una biblioteca. Include una classe Libri per rappresentare i singoli libri e una classe ListaLibri per gestire un array di libri, con funzionalità di aggiunta, rimozione e visualizzazione.
Struttura del Progetto
src/main/java/:
Libri.java: Classe che rappresenta un libro.

ListaLibri.java: Classe che gestisce un array di libri.

Main.java: Punto di ingresso per creare, inizializzare e stampare libri.

pom.xml: Configura il progetto con Java (versione non specificata, assumiamo Java 23).

Dettagli delle Classi
Libri.java
Descrizione: Rappresenta un libro con attributi e metodi.

Attributi:
titolo (String): Titolo del libro.

autore (String): Autore del libro.

annoPubblicazione (int): Anno di pubblicazione.

ISBN (String): Codice ISBN.

Metodi:
Costruttore: Inizializza un libro con titolo, autore, anno e ISBN.

Getter/Setter: Per accedere e modificare gli attributi.

toString(): Restituisce una stringa con i dettagli del libro.

ListaLibri.java
Descrizione: Gestisce un array di oggetti Libri.

Metodi:
aggiungi(Libri libro): Aggiunge un libro all’array.

rimuovi(int indice): Rimuove un libro dall’array in base all’indice.

get(int indice): Restituisce il libro all’indice specificato.

elencoLibri(): Restituisce un array di stringhe con i titoli dei libri.

Main.java
Descrizione: Punto di ingresso del programma.

Funzionalità:
Crea oggetti Libri con dati specifici (es. titolo, autore, anno, ISBN).

Inizializza un’istanza di ListaLibri.

Aggiunge i libri alla lista.

Recupera e stampa i titoli con elencoLibri() e i dettagli con toString().

Esecuzione
Compilare ed eseguire il programma con un IDE o tramite javac e java.

Output: Mostra i dettagli dei libri e l’elenco dei titoli a schermo.

Esempio di Utilizzo
Crea libri: Libri libro1 = new Libri("Il Nome del Vento", "Patrick Rothfuss", 2007, "978-88-04-57352-4");.

Aggiungi alla lista: lista.aggiungi(libro1);.

Stampa: System.out.println(libro1); e System.out.println(Arrays.toString(lista.elencoLibri()));.


Progetto Biblioteca - README
Descrizione
Questo è un progetto Java che simula una biblioteca con gestione di libri, utenti e prestiti. Include classi di modello e un programma principale, con test automatizzati usando JUnit 5.
Struttura del Progetto
src/main/java/com/biblioteca/model/: Contiene le classi logiche:
Biblioteca.java: Gestisce un array di libri (aggiunta, rimozione, elenco).

Libro.java: Rappresenta un libro (titolo, autore, anno, ISBN).

Utente.java: Rappresenta un utente (nome, cognome, ID).

Prestito.java: Gestisce prestiti e restituzioni di libri.

src/main/java/com/biblioteca/main/: Contiene Main.java, il punto di ingresso che integra le classi in un flusso applicativo.

src/test/java/com/biblioteca/model/: Test per le classi di modello (BibliotecaTest, LibroTest, UtenteTest, PrestitoTest).

src/test/java/com/biblioteca/main/: Test per Main.java (MainTest).

pom.xml: Configura il progetto con Java 23, JUnit 5.10.0 e maven-surefire-plugin.

Funzionalità Principali
Aggiunta e rimozione di libri nella biblioteca (max 100).

Creazione e modifica di libri e utenti.

Prestito e restituzione di libri con controlli.

Stampa dell’elenco dei libri e dettagli degli utenti.

Test
BibliotecaTest: Verifica gestione array e elenco.

LibroTest: Controlla costruttori, getter/setter, toString().

UtenteTest: Testa creazione, modifica e indipendenza tra istanze.

PrestitoTest: Verifica ricerca, prestito e restituzione.

MainTest: Simula il flusso completo e cattura l’output.

Processi: Usa @BeforeEach per reset, asserzioni JUnit per verifica, @AfterEach per pulizia (es. System.out).

Esecuzione
Compilare ed eseguire i test con: mvn test.

Report dei test in target/surefire-reports.


