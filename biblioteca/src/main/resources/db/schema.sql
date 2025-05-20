-- creazione db

-- libri
CREATE TABLE libri (
titolo VARCHAR (100),
autore VARCHAR (100),
anno_pubblicazione INT,
isbn VARCHAR (20) PRIMARY KEY);

-- utenti
CREATE TABLE utenti (
id_utente INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
cognome VARCHAR(100)
);