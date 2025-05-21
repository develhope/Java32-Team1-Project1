-- insert

-- libri
INSERT INTO biblioteca.libri (titolo, autore, anno_pubblicazione, isbn) VALUES
('Quello che so di te', 'Nadia Terranova', 2025, 9788823521234),
('Fratellino', 'Ibrahima Balde e Amets', 2025, 9788807895678),
('Macroeconomia', 'N. Gregory Manki', 2016, 9788880085096),
('Il nome della rosa', 'Umberto Eco', 1980, 9788845240000);

-- utenti
INSERT INTO biblioteca.utenti (nome, cognome)
VALUES ('Mario', 'Rossi'),
('Sheldon', 'Cooper'),
('Leonard', 'Hoffsteader'),
('Bianca', 'Piazzolla'),
('Mario', 'Draghi');

INSERT INTO prestiti (data_prestito, isbn, id_utente)
VALUES (NOW(), '9788845240000', 5),
(NOW(), '9788807895678', 3),
(NOW(), '9788880085096',4),
(NOW(), '9788823521234', 4),
(NOW(), '9788845240000', 1),
(NOW(), '9788880085096', 5),
(NOW(), '9788807895678', 2),
(NOW(), '9788845240000', 3),
(NOW(), '9788823521234', 1),
(NOW(), '9788880085096', 2)
;
