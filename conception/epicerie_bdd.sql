#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

#------------------------------------------------------------
# Table: Utilisateurs
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Utilisateurs(
        utls_id        Int  Auto_increment  NOT NULL ,
        utls_nom       Varchar (200) NOT NULL ,
        utls_prenom    Varchar (200) NOT NULL ,
        utls_telephone Varchar (50) NOT NULL ,
        utls_mail      Varchar (200) NOT NULL ,
        utls_adresse   Varchar (200) NOT NULL ,
        utls_mdp       Varchar (200) NOT NULL ,
        utls_caisse    Varchar (10) NOT NULL ,
        utls_manager   Bool Default false NOT NULL
	,CONSTRAINT Utilisateurs_PK PRIMARY KEY (utls_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Tickets
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Tickets(
        tckt_id          Int  Auto_increment  NOT NULL ,
        tckt_date        Date NOT NULL ,
        tck_statut       Varchar (50) NOT NULL ,
        tckt_commentaire Varchar (50) ,
        utls_id          Int NOT NULL
	,CONSTRAINT Tickets_PK PRIMARY KEY (tckt_id)

	,CONSTRAINT Tickets_Utilisateurs_FK FOREIGN KEY (utls_id) REFERENCES Utilisateurs(utls_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Categories
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Categories(
        cate_id      Int  Auto_increment  NOT NULL ,
        cate_libelle Varchar (200) NOT NULL
	,CONSTRAINT Categories_PK PRIMARY KEY (cate_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Insertion des premières catégories de produits
#------------------------------------------------------------


INSERT INTO Categories VALUES(1, "Fruits");
INSERT INTO Categories VALUES(2, "Légumes");
INSERT INTO Categories VALUES(3, "Boucherie");
INSERT INTO Categories VALUES(4, "Spiritueux");
INSERT INTO Categories VALUES(5, "Sauces");
INSERT INTO Categories VALUES(6, "Epices");
INSERT INTO Categories VALUES(7, "Produits ménagers");
INSERT INTO Categories VALUES(8, "Pâtes");
INSERT INTO Categories VALUES(9, "Surgelés");
INSERT INTO Categories VALUES(10, "Fromages");
INSERT INTO Categories VALUES(11, "Charcuteries");
INSERT INTO Categories VALUES(12, "Hygiène et Beauté");
INSERT INTO Categories VALUES(13, "Friandises");
INSERT INTO Categories VALUES(14, "Pains et pâtisseries");
INSERT INTO Categories VALUES(15, "Boissons sans alcool");



#------------------------------------------------------------
# Table: Produits
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Produits(
        prod_id             Int  Auto_increment  NOT NULL ,
        prod_nom            Varchar (200) NOT NULL ,
        prod_description    Varchar (1000) NOT NULL ,
        prod_prix_vente_ttc Float NOT NULL ,
        prod_quantite_min   Int NOT NULL ,
        prod_quantite_stock Float NOT NULL ,
        prod_unite          Varchar (50) NOT NULL ,
        prod_statut         Varchar (50) NOT NULL ,
        cate_id             Int NOT NULL
	,CONSTRAINT Produits_PK PRIMARY KEY (prod_id)

	,CONSTRAINT Produits_Categories_FK FOREIGN KEY (cate_id) REFERENCES Categories(cate_id) ON DELETE CASCADE
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Tickets_Produits
#------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Tickets_Produits(
        tckt_id            Int NOT NULL ,
        prod_id            Int NOT NULL ,
        tckt_prod_id       Int Auto_increment NOT NULL ,
        tckt_prod_quantite Float NOT NULL
	,CONSTRAINT Tickets_Produits_PK PRIMARY KEY (tckt_id,prod_id)
    ,KEY(tckt_prod_id)

	,CONSTRAINT Tickets_Produits_Tickets_FK FOREIGN KEY (tckt_id) REFERENCES Tickets(tckt_id) ON DELETE CASCADE ON UPDATE CASCADE
	,CONSTRAINT Tickets_Produits_Produits0_FK FOREIGN KEY (prod_id) REFERENCES Produits(prod_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

#------------------------------------------------------------
# Insertion du premier utilisateur root pour le premier démarrage
#------------------------------------------------------------

REPLACE INTO Utilisateurs(utls_id ,utls_nom, utls_prenom, utls_telephone, utls_mail, utls_adresse, utls_mdp, utls_caisse, utls_manager) VALUES (1, "root", "root", "", "", "", "root", 0, true);

#------------------------------------------------------------
# Insertion des premiers produits de base
#------------------------------------------------------------
REPLACE INTO Produits VALUES(1, 'Pomme rouge', 'Pomme rouge, Origine France', 2, 15, 0, 'kg', 'Rupture', 1);















