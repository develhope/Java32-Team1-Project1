-- creazione db


CREATE TABLE libri (
titolo VARCHAR (100),
autore VARCHAR (100),
anno_pubblicazione INT,
isbn VARCHAR (20) PRIMARY KEY);

CREATE TABLE utenti (
id_utente INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100),
cognome VARCHAR (100)
);
