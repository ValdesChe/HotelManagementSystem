CREATE TABLE alphahotel.utilisateur
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  nom VARCHAR(30),
  prenom VARCHAR(30),
  telephone VARCHAR(30),
  login VARCHAR(35),
  email VARCHAR(35) NULL,
  password_hash VARCHAR(150),
  created_at DATE ,
  updated_at DATE,
  status VARCHAR(20),
  role VARCHAR(20)
);
CREATE UNIQUE INDEX utilisateur_id_uindex ON alphahotel.utilisateur (id);
CREATE UNIQUE INDEX utilisateur_login_uindex ON alphahotel.utilisateur (login);
CREATE UNIQUE INDEX utilisateur_email_uindex ON alphahotel.utilisateur (email);