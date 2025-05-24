-- insert

-- libri
INSERT INTO biblioteca.libri (titolo, autore, anno_pubblicazione, isbn, numero_copie) VALUES
('Quello che so di te', 'Nadia Terranova', 2025, 9788823521234, 12),
('Fratellino', 'Ibrahima Balde e Amets', 2025, 9788807895678, 24),
('Macroeconomia', 'N. Gregory Manki', 2016, 9788880085096, 8),
('Il nome della rosa', 'Umberto Eco', 1980, 9788845240000, 7);

-- utenti
INSERT INTO biblioteca.utenti (nome, cognome)
VALUES ('Mario', 'Rossi'),
('Sheldon', 'Cooper'),
('Leonard', 'Hoffsteader'),
('Bianca', 'Piazzolla'),
('Mario', 'Draghi');


INSERT INTO prestiti (isbn, id_utente)
VALUES ('9788845240000', 5),
('9788807895678', 3),
('9788880085096',4),
('9788823521234', 4),
('9788845240000', 1),
('9788880085096', 5),
('9788807895678', 2),
('9788845240000', 3),
('9788823521234', 1),
('9788880085096', 2);

