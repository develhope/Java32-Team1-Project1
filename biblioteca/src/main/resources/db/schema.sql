-- creazione db

-- libri
CREATE TABLE libri (
titolo VARCHAR (100),
autore VARCHAR (100),
anno_pubblicazione INT,
numero_copie INT NOT NULL,
isbn VARCHAR (20) PRIMARY KEY);

-- utenti
CREATE TABLE utenti (
id_utente INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
cognome VARCHAR(100)
);

-- prestiti
CREATE TABLE prestiti (
id_prestito int auto_increment PRIMARY KEY ,
data_prestito datetime not null default now(),
data_restituzione datetime ,
isbn varchar(20) not null ,
id_utente int not null ,
foreign key (isbn) references libri (isbn) ,
foreign key (id_utente) references utenti (id_utente)
);

) ;
