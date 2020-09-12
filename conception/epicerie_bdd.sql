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
        tckt_statut       Varchar (50) NOT NULL ,
        tckt_commentaire Varchar (50) ,
        tckt_montant_ttc Float NOT NULL,
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

	,CONSTRAINT Produits_Categories_FK FOREIGN KEY (cate_id) REFERENCES Categories(cate_id) ON DELETE CASCADE ON UPDATE CASCADE
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
# Insertion du premier utilisateur root pour le premier lancement
#------------------------------------------------------------

INSERT IGNORE INTO Utilisateurs(utls_id ,utls_nom, utls_prenom, utls_telephone, utls_mail, utls_adresse, utls_mdp, utls_caisse, utls_manager) VALUES (1, "root", "root", "", "", "", "root", 0, true);

#------------------------------------------------------------
# Insertion des premieres categories de produits
#------------------------------------------------------------

INSERT IGNORE INTO Categories VALUES(1, "Fruits");
INSERT IGNORE INTO Categories VALUES(2, "Legumes");
INSERT IGNORE INTO Categories VALUES(3, "Boucherie");
INSERT IGNORE INTO Categories VALUES(4, "Spiritueux");
INSERT IGNORE INTO Categories VALUES(5, "Sauces");
INSERT IGNORE INTO Categories VALUES(6, "Epices");
INSERT IGNORE INTO Categories VALUES(7, "Produits menagers");
INSERT IGNORE INTO Categories VALUES(8, "Pates");
INSERT IGNORE INTO Categories VALUES(9, "Surgeles");
INSERT IGNORE INTO Categories VALUES(10, "Fromages");
INSERT IGNORE INTO Categories VALUES(11, "Charcuteries");
INSERT IGNORE INTO Categories VALUES(12, "Hygiene et Beaute");
INSERT IGNORE INTO Categories VALUES(13, "Friandises");
INSERT IGNORE INTO Categories VALUES(14, "Pains et patisseries");
INSERT IGNORE INTO Categories VALUES(15, "Boissons sans alcool");

#------------------------------------------------------------
# Insertion des premiers produits de base
#------------------------------------------------------------

INSERT IGNORE INTO Produits VALUES(1, 'Orange', 'Orange origine Madagascar', 4, 20, 25, 'kg', 'En stock', 1);
INSERT IGNORE INTO Produits VALUES(2, 'Framboise', 'Frambroise origine France', 25, 10, 15, 'kg', 'En stock', 1);
INSERT IGNORE INTO Produits VALUES(3, 'Mangue', 'Mangue origine Espagne', 25, 10, 15, 'kg', 'En stock', 1);
INSERT IGNORE INTO Produits VALUES(4, 'Banane cavendish', 'Banane cavendish origine Mexique', 3, 20, 25, 'kg', 'En stock', 1);
INSERT IGNORE INTO Produits VALUES(5, 'Tomate', 'Tomate origine France', 2, 20, 25, 'kg', 'En stock', 1);
INSERT IGNORE INTO Produits VALUES(6, 'Avocat', 'Avocat origine Maroc', 4, 15, 20, 'paire', 'En stock', 1);
INSERT IGNORE INTO Produits VALUES(7, 'Salade iceberg', 'Salade origine France', 5, 10, 15, 'kg', 'En stock', 2);
INSERT IGNORE INTO Produits VALUES(8, 'Aubergine', 'Aubergine Origine France', 5, 10, 15, 'kg', 'En stock', 2);
INSERT IGNORE INTO Produits VALUES(9, 'Pomme de terre', 'Petites pomme de terre jaune', 2, 25, 10, 'kg', 'A commander', 2);
INSERT IGNORE INTO Produits VALUES(10, 'Ail', 'Ail blanc', 15, 10, 15, 'kg', 'En stock', 2);
INSERT IGNORE INTO Produits VALUES(11, 'Oignon', 'Oignon jaune', 4, 15, 20, 'kg', 'En stock', 2);
INSERT IGNORE INTO Produits VALUES(12, 'Echalotte', 'Echalotte origine Maroc', 4, 15, 10, 'kg', 'A commander', 2);
INSERT IGNORE INTO Produits VALUES(13, 'Merguez', 'Barquette de 6 merguez', 25, 10, 15, 'barquette', 'En stock', 3);
INSERT IGNORE INTO Produits VALUES(14, 'Entrecote', 'Viande origine France', 50, 10, 15, 'kg', 'En stock', 3);
INSERT IGNORE INTO Produits VALUES(15, 'Steak hache', 'Barquette de 2. Viande origine France', 4, 10, 0, 'barquette', 'Rupture', 3);
INSERT IGNORE INTO Produits VALUES(16, 'Blanc de poulet', 'Barquette de 2. Viande origine France', 6, 10, 15, 'barquette', 'En stock', 3);
INSERT IGNORE INTO Produits VALUES(17, 'Jack Daniels', 'Bourbon', 25, 10, 15, 'bouteille', 'En stock', 4);
INSERT IGNORE INTO Produits VALUES(18, 'Absolut Vodka', 'Vodka premium', 30, 10, 15, 'bouteille', 'En stock', 4);
INSERT IGNORE INTO Produits VALUES(19, 'Prosecco', 'Vin blanc effervescent italien', 20, 10, 15, 'bouteille', 'En stock', 4);
INSERT IGNORE INTO Produits VALUES(20, 'Villageoise', 'Vin rouge de luxe', 10, 10, 0, 'bouteille', 'Rupture', 4);
INSERT IGNORE INTO Produits VALUES(21, 'Ketchup', 'Sauce de tomate sucrée', 4, 10, 15, 'bouteille', 'En stock', 5);
INSERT IGNORE INTO Produits VALUES(22, 'Mayonnaise', 'Sauce froide a base d huile, œuf, vinaigre et citron', 4, 10, 15, 'pot', 'En stock', 5);
INSERT IGNORE INTO Produits VALUES(23, 'Moutarde', 'Moutarde de Dijon', 4, 10, 15, 'pot', 'En stock', 5);
INSERT IGNORE INTO Produits VALUES(24, 'Sel marin', 'Sel fin iode', 3, 10, 15, 'bouteille', 'En stock', 6);
INSERT IGNORE INTO Produits VALUES(25, 'Poivre', 'Poivre moulu', 5, 10, 0, 'bouteille', 'Rupture', 6);
INSERT IGNORE INTO Produits VALUES(26, 'Piment moulu', 'Piment origine Chine moulu', 5, 10, 15, 'bouteille', 'En stock', 6);
INSERT IGNORE INTO Produits VALUES(27, 'Liquide vaisselle', 'Liquide pour nettoyage vaisselle', 5, 10, 15, 'bouteille', 'En stock', 7);
INSERT IGNORE INTO Produits VALUES(28, 'Adoucissant vetement', 'Adoucissant machine a laver', 12, 10, 15, 'bouteille', 'En stock', 7);
INSERT IGNORE INTO Produits VALUES(29, 'Savon liquide', 'Savon tout usage', 4, 10, 15, 'bouteille', 'En stock', 7);
INSERT IGNORE INTO Produits VALUES(30, 'Spaghetti', 'Paquet de 400g', 3, 10, 2, 'paquet', 'A commander', 8);
INSERT IGNORE INTO Produits VALUES(31, 'Pate sans gluten', 'Paquet de 400g. Pour les coeliaque', 6, 10, 15, 'paquet', 'En stock', 8);
INSERT IGNORE INTO Produits VALUES(32, 'Steak surgele', 'Paquet de 6 steak', 5, 10, 15, 'paquet', 'En stock', 9);
INSERT IGNORE INTO Produits VALUES(33, 'Filet de dorade surgele', 'Paquet de 4 filets', 7, 10, 5, 'paquet', 'A commander', 9);
INSERT IGNORE INTO Produits VALUES(34, 'Legume prepare surgele', 'Paquet de 400g.', 8, 10, 15, 'paquet', 'En stock', 9);
INSERT IGNORE INTO Produits VALUES(35, 'Camembert', 'Le plaisir dun camembert moelleux et a la saveur unique.', 5, 10, 15, 'paquet', 'En stock', 10);
INSERT IGNORE INTO Produits VALUES(36, 'Buche de chevre', 'Fromage de chevre', 5, 10, 15, 'paquet', 'En stock', 10);
INSERT IGNORE INTO Produits VALUES(37, 'Brie', 'Fromage au lait cru', 5, 10, 0, 'paquet', 'Rupture', 10);
INSERT IGNORE INTO Produits VALUES(38, 'Emental', 'Fromage pas cher', 3, 10, 15, 'paquet', 'En stock', 10);
INSERT IGNORE INTO Produits VALUES(39, 'Saucisson sec', 'Pour l apero', 7, 10, 15, 'baton', 'En stock', 11);
INSERT IGNORE INTO Produits VALUES(40, 'Jambon sec italien', 'Pour l apero', 20, 10, 15, 'barquette', 'En stock', 11);
INSERT IGNORE INTO Produits VALUES(41, 'Creme visage', 'Attention au visage', 8, 10, 15, 'pot', 'En stock', 12);
INSERT IGNORE INTO Produits VALUES(42, 'Dentifrice', 'Pour pas puer de la gueule', 2, 10, 0, 'tube', 'Rupture', 12);
INSERT IGNORE INTO Produits VALUES(43, 'Gel cheveux', 'Pour etre beau', 6, 10, 15, 'pot', 'En stock', 12);
INSERT IGNORE INTO Produits VALUES(44, 'Bonbon tagada', 'Gaffe aux chichots', 3, 10, 15, 'sachet', 'En stock', 13);
INSERT IGNORE INTO Produits VALUES(45, 'Chocolat Lint', 'Gaffe aux chichots', 4, 10, 2, 'tablette', 'A commander', 13);
INSERT IGNORE INTO Produits VALUES(46, 'Brioche', 'Pour le matin', 3, 10, 15, 'sachet', 'En stock', 14);
INSERT IGNORE INTO Produits VALUES(47, 'Pain', 'Baguette', 1, 10, 3, 'sachet', 'A commander', 14);
INSERT IGNORE INTO Produits VALUES(48, 'Jus orange', 'Pour couper la vodka', 2, 10, 15, 'bouteille', 'En stock', 15);
INSERT IGNORE INTO Produits VALUES(49, 'Coca', 'Pour couper le whisky', 2, 10, 15, 'bouteille', 'En stock', 15);
INSERT IGNORE INTO Produits VALUES(50, 'Jus mix fruits', 'Pour couper la vodka', 2, 10, 9, 'bouteille', 'A commander', 15);

#------------------------------------------------------------
# Insertion des premiers tickets
#------------------------------------------------------------

INSERT IGNORE INTO Tickets VALUES (1, '2020-09-01', 'fermé', NULL, '20', '1');
