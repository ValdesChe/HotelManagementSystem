CREATE TABLE IF NOT EXISTS alphahotel.utilisateur
(
  id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
  nom VARCHAR(30),
  prenom VARCHAR(30),
  telephone VARCHAR(30),
  login VARCHAR(35) UNIQUE,
  email VARCHAR(35) NULL UNIQUE,
  password_hash VARCHAR(150),
  created_at DATE ,
  updated_at DATE,
  status VARCHAR(20),
  role VARCHAR(20)
);
/*
CREATE UNIQUE INDEX  utilisateur_id_uindex ON alphahotel.utilisateur (id);
CREATE UNIQUE INDEX utilisateur_login_uindex ON alphahotel.utilisateur (login);
CREATE UNIQUE INDEX utilisateur_email_uindex ON alphahotel.utilisateur (email);
*/

create table chambre
(
  id int auto_increment
    primary key,
  utilisateur_id int null,
  libele varchar(30) default 'CHAMBRE' null,
  descrip varchar(150) default 'Chambre très spacieuse, idéale pour votre séjour privé' null,
  type varchar(30) default 'DEFAULT' null,
  photo_url varchar(150) default 'DEFAULT' null,
  prix_min int default '150' null,
  statut varchar(20) default 'ACTIVE' null,
  created_at datetime default CURRENT_TIMESTAMP null,
  updated_at datetime default CURRENT_TIMESTAMP null,
  constraint chambre_libele_uindex
  unique (libele),
  constraint utilisateur_id_fk
  foreign key (utilisateur_id) references alphahotel.utilisateur (id)
)
;

create index utilisateur_id_fk
  on chambre (utilisateur_id)
;



CREATE TABLE alphahotel.reservation
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  nomcl VARCHAR(30),
  prenomcl VARCHAR(30),
  numpassport VARCHAR(30),
  nbnuit INT,
  date_debut DATETIME DEFAULT current_timestamp NOT NULL,
  date_fin DATETIME DEFAULT current_timestamp,
  total INT,
  statut VARCHAR(30) DEFAULT "PENDING",
  commercial_confirm INT,
  comptable_bill INT,
  chambre_id INT,
  created_at DATETIME DEFAULT current_timestamp NOT NULL,
  update_at DATETIME DEFAULT current_timestamp,
  CONSTRAINT commercial_id_fk FOREIGN KEY (commercial_confirm) REFERENCES utilisateur (id),
  CONSTRAINT comptable_id_fk FOREIGN KEY (comptable_bill) REFERENCES utilisateur (id),
  CONSTRAINT chambre_id_fk FOREIGN KEY (chambre_id) REFERENCES chambre (id)
);
ALTER TABLE alphahotel.reservation COMMENT = 'Table de reservation des clients';

