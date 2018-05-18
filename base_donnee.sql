SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS Utilisateurs, Fichiers, Appartient;

SET FOREIGN_KEY_CHECKS=1;



CREATE TABLE IF NOT EXISTS Utilisateurs (
       id_u INT NOT NULL AUTO_INCREMENT,
       email VARCHAR(50) NOT NULL,
       mdp VARCHAR(30) NOT NULL,
       PRIMARY KEY (id_u)
);

CREATE TABLE IF NOT EXISTS Fichiers(
       id_f INT NOT NULL AUTO_INCREMENT,
       titre VARCHAR(100) NOT NULL,
       contenu VARCHAR(9999) NOT NULL,
       PRIMARY KEY (id_f)
);

CREATE TABLE IF NOT EXISTS Appartient(
       id_u INT NOT NULL,
       id_f INT NOT NULL,
       FOREIGN KEY (id_u) REFERENCES Utilisateurs(id_u),
       FOREIGN KEY (id_f) REFERENCES Fichiers(id_f)
);

INSERT INTO Utilisateurs(email,mdp) VALUES ("a","a");
INSERT INTO Utilisateurs(email,mdp) VALUES ("guillaumeromero@free.fr", "MagikarpIsBest");
INSERT INTO Utilisateurs(email,mdp) VALUES ("maleine.patural@metaleuse.fr", "PowerWolf");
INSERT INTO Utilisateurs(email,mdp) VALUES ("kimrubiks@nuclear.fr", "Naruto");
INSERT INTO Utilisateurs(email,mdp) VALUES ("adamptisuisse@hotmail.ch", "Chocolat");

INSERT INTO Fichiers(titre,contenu) VALUES ("BlahBlah", "Ceci est un test");

INSERT INTO Appartient(id_u,id_f) VALUES (2,1);
INSERT INTO Appartient(id_u,id_f) VALUES (3,1);
INSERT INTO Appartient(id_u,id_f) VALUES (4,1);
INSERT INTO Appartient(id_u,id_f) VALUES (5,1);