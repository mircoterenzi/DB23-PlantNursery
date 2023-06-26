DROP DATABASE IF EXISTS PlantNursery;

CREATE DATABASE IF NOT EXISTS PlantNursery;

USE PlantNursery;

CREATE TABLE Accessorio (
     id_prodotto INT NOT NULL,
     descrizione VARCHAR(20) NOT NULL,
     id_fattura NUMERIC not null,
     id_scontrino NUMERIC,
     tipo VARCHAR(20) NOT NULL,
     PRIMARY KEY (id_prodotto)
);

CREATE TABLE Cura (
     pianta NUMERIC NOT NULL,
     data DATE NOT NULL,
     id_imp NUMERIC NOT NULL,
     concime boolean NOT NULL,
     PRIMARY KEY (pianta, data)
);

CREATE TABLE Fattura (
     id_documento NUMERIC NOT NULL,
     data DATE NOT NULL,
     id_fornitore NUMERIC NOT NULL,
     PRIMARY KEY (id_documento)
);

CREATE TABLE Fornitore (
     id_fornitore NUMERIC NOT NULL,
     nome VARCHAR(20) NOT NULL,
     PRIMARY KEY (id_fornitore)
);

CREATE TABLE Impiegato (
     nome VARCHAR(20) NOT NULL,
     cognome VARCHAR(20) NOT NULL,
     CF VARCHAR(20) NOT NULL,
     stipendio FLOAT NOT NULL,
     data_assunzione DATE NOT NULL,
     id_imp NUMERIC NOT NULL,
     PRIMARY KEY (id_imp),
     CONSTRAINT SID_Impiegato_ID UNIQUE (CF)
);

create table Piano_di_Cura (
     id_piano numeric not null,
     acqua numeric not null,
     livello_luce varchar(20) not null,
     concime numeric not null,
     temp_min float not null,
     temp_max float not null,
     constraint ID_Piano_di_Cura_ID primary key (id_piano));

create table Pianta (
     id_prodotto numeric not null,
     descrizione varchar(20) not null,
     larghezza_vaso float not null,
     altezza float not null,
     prezzo FLOAT(8) not null,
     id_fattura numeric not null,
     id_scontrino numeric,
     nome varchar(20) not null,
     constraint ID_Pianta_ID primary key (id_prodotto));

create table Reparto (
     cod_reparto numeric not null,
     nome_reparto varchar(20) not null,
     constraint ID_Reparto_ID primary key (cod_reparto));

create table Scontrino (
     id_documento numeric not null,
     data date not null,
     impiegato numeric not null,
     constraint ID_Scontrino_ID primary key (id_documento));

create table Tipo_accessorio (
     nome_tipo varchar(20) not null,
     prezzo FLOAT(8) not null,
     reparto numeric not null,
     constraint ID_Tipo_accessorio_ID primary key (nome_tipo));

create table Tipo_Pianta (
     nome_scientifico varchar(20) not null,
     piano numeric not null,
     reparto numeric not null,
     constraint ID_Tipo_Pianta_ID primary key (nome_scientifico));

create table Turno (
     cod_reparto numeric not null,
     data date not null,
     ora_inizio numeric not null,
     ora_fine numeric not null,
     id_imp numeric not null,
     constraint ID_Turno_ID primary key (cod_reparto, data, ora_inizio));


-- Constraints Section
-- ___________________ 

alter table Cura add CONSTRAINT FK_Cura_Impiegato 
		FOREIGN KEY (id_imp) 
        REFERENCES Impiegato(id_imp);

alter table Accessorio add constraint EQU_Acces_Fattu_FK
     foreign key (id_fattura)
     references Fattura(id_documento);

alter table Accessorio add constraint REF_Acces_Scont_FK
     foreign key (id_scontrino)
     references Scontrino(id_documento);

alter table Accessorio add constraint REF_Acces_Tipo__FK
     foreign key (tipo)
     references Tipo_accessorio(nome_tipo);

alter table Cura add constraint REF_Cura_Piant
     foreign key (pianta)
     references Pianta(id_prodotto);

alter table Fattura add constraint REF_Fattu_Forni_FK
     foreign key (id_fornitore)
     references Fornitore(id_fornitore);

alter table Pianta add constraint REF_Piant_Fattu_FK
     foreign key (id_fattura)
     references Fattura(id_documento);

alter table Pianta add constraint REF_Piant_Scont_FK
     foreign key (id_scontrino)
     references Scontrino(id_documento);

alter table Pianta add constraint REF_Piant_Tipo__FK
     foreign key (nome)
     references Tipo_Pianta(nome_scientifico);

alter table Scontrino add constraint REF_Scont_Impie_FK
     foreign key (impiegato)
     references Impiegato(id_imp);

alter table Tipo_accessorio add constraint REF_Tipo__Repar_FK
     foreign key (reparto)
     references Reparto(cod_reparto);
	
alter table Tipo_Pianta add constraint REF_Tipo__Pianta_FK
     foreign key (reparto)
     references Reparto(cod_reparto);

alter table Tipo_Pianta add constraint REF_Tipo__Piano_FK
     foreign key (piano)
     references Piano_di_Cura(id_piano);

alter table Turno add constraint REF_Turno_Repar
     foreign key (cod_reparto)
     references Reparto(cod_reparto);

alter table Turno add constraint REF_Turno_Impie_FK
     foreign key (id_imp)
     references Impiegato(id_imp);


-- Index Section
-- _____________ 

create unique index ID_Accessorio_IND
     on Accessorio (id_prodotto);

create index EQU_Acces_Fattu_IND
     on Accessorio (id_fattura);

create index REF_Acces_Scont_IND
     on Accessorio (id_scontrino);

create index REF_Acces_Tipo__IND
     on Accessorio (tipo);

create unique index ID_Cura_IND
     on Cura (pianta, data);

create unique index ID_Fattura_IND
     on Fattura (id_documento);

create index REF_Fattu_Forni_IND
     on Fattura (id_fornitore);

create unique index ID_Fornitore_IND
     on Fornitore (id_fornitore);

create unique index ID_Impiegato_IND
     on Impiegato (id_imp);

create unique index SID_Impiegato_IND
     on Impiegato (CF);

create unique index ID_Piano_di_Cura_IND
     on Piano_di_Cura (id_piano);

create index REF_Piant_Fattu_IND
     on Pianta (id_fattura);

create index REF_Piant_Scont_IND
     on Pianta (id_scontrino);

create index REF_Piant_Tipo__IND
     on Pianta (nome);

create unique index ID_Pianta_IND
     on Pianta (id_prodotto);

create unique index ID_Reparto_IND
     on Reparto (cod_reparto);

create unique index ID_Scontrino_IND
     on Scontrino (id_documento);

create index REF_Scont_Impie_IND
     on Scontrino (impiegato);

create unique index ID_Tipo_accessorio_IND
     on Tipo_accessorio (nome_tipo);

create index REF_Tipo__Repar_IND
     on Tipo_accessorio (reparto);

create unique index ID_Tipo_Pianta_IND
     on Tipo_Pianta (nome_scientifico);

create index REF_Tipo__Piano_IND
     on Tipo_Pianta (piano);

create unique index ID_Turno_IND
     on Turno (cod_reparto, data, ora_inizio);

create index REF_Turno_Impie_IND
     on Turno (id_imp);

-- Triggers
DELIMITER //

CREATE TRIGGER check_uniqueness_insert_accessorio
BEFORE INSERT ON Accessorio
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM Pianta
    WHERE id_prodotto = NEW.id_prodotto
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Duplicate id_prodotto found across tables';
  END IF;
END//

CREATE TRIGGER check_uniqueness_update_accessorio
BEFORE UPDATE ON Accessorio
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM Pianta
    WHERE id_prodotto = NEW.id_prodotto
      AND id_prodotto <> OLD.id_prodotto
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Duplicate id_prodotto found across tables';
  END IF;
END//

CREATE TRIGGER check_uniqueness_insert_pianta
BEFORE INSERT ON Pianta 
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM Accessorio
    WHERE id_prodotto = NEW.id_prodotto
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Duplicate id_prodotto found across tables';
  END IF;
END//

CREATE TRIGGER check_uniqueness_update_pianta
BEFORE UPDATE ON Pianta
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM Accessorio
    WHERE id_prodotto = NEW.id_prodotto
      AND id_prodotto <> OLD.id_prodotto
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Duplicate id_prodotto found across tables';
  END IF;
END//

DELIMITER ;

DELIMITER //

CREATE TRIGGER check_uniqueness_insert_fattura
BEFORE INSERT ON Fattura
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM Scontrino
    WHERE id_documento = NEW.id_documento
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Duplicate id_documento found across tables';
  END IF;
END//

CREATE TRIGGER check_uniqueness_update_fattura
BEFORE UPDATE ON Fattura
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM Scontrino
    WHERE id_documento = NEW.id_documento
      AND id_documento <> OLD.id_documento
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Duplicate id_documento found across tables';
  END IF;
END//

CREATE TRIGGER check_uniqueness_insert_scontrino
BEFORE INSERT ON Scontrino
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM Fattura
    WHERE id_documento = NEW.id_documento
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Duplicate id_documento found across tables';
  END IF;
END//

CREATE TRIGGER check_uniqueness_update_scontrino
BEFORE UPDATE ON Scontrino
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM Fattura
    WHERE id_documento = NEW.id_documento
      AND id_documento <> OLD.id_documento
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Duplicate id_documento found across tables';
  END IF;
END//

DELIMITER ;
