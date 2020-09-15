package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProduitsActions {
	
	public static boolean ajouterProduit(int id, String nom, String desc, float prixVente, int qteMin, float qteStock, String unite, String statut, int cateID)
	{
		try {
			Connection cnx = DBConnection.ConnectToDatabase();
			Statement st = cnx.createStatement();

			Produits p = new Produits(id, nom, desc, prixVente, qteMin, qteStock, unite, statut, cateID);
			String insertStatement = p.createInsertStatement();
			
			System.out.println(insertStatement);

			st.execute(insertStatement);
			cnx.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static List<Produits> getProduitsDB()
	{
		List<Produits> listeProduits = new ArrayList<Produits>();
		
		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les produits
			ResultSet resProduits = st.executeQuery("SELECT * FROM produits");
			while (resProduits.next()) {
				int prod_id = resProduits.getInt("prod_id");
				String prod_nom = resProduits.getString("prod_nom");
				String prod_description = resProduits.getString("prod_description");
				int prod_prix_vente_ttc = resProduits.getInt("prod_prix_vente_ttc");
				int prod_quantite_min = resProduits.getInt("prod_quantite_min");
				float prod_quantite_stock = resProduits.getFloat("prod_quantite_stock");
				String prod_unite = resProduits.getString("prod_unite");
				String prod_statut = resProduits.getString("prod_statut");
				int prod_cate_id = resProduits.getInt("cate_id");
				
				
				Produits u = new Produits(prod_id, prod_nom, prod_description, prod_prix_vente_ttc, prod_quantite_min, prod_quantite_stock, prod_unite, prod_statut, prod_cate_id);
				listeProduits.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeProduits;
	}
	
	public static ResultSet getProduitSingleByName(String nomProduit) {
		ResultSet getProduitSingle = null;

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les produits
			getProduitSingle = st.executeQuery("SELECT " + 
					"    prod_nom AS Nom," + 
					"    prod_prix_vente_ttc AS Prix," + 
					"    prod_quantite_min AS Quantité_min," + 
					"    prod_quantite_stock AS Stock," + 
					"    prod_unite AS Unité_Mesure," +
					"    prod_statut AS Statut " +
					"FROM" + 
					"    produits" + 
					"        WHERE" + 
					"    prod_nom = '" + nomProduit + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getProduitSingle;
	}
	
	public static Produits getProduitSingleById(int id) {
		Produits p = null;

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les produits
			ResultSet getProduitSingle = st.executeQuery("SELECT * FROM produits WHERE prod_id = '" + Integer.toString(id) + "'");
			if (getProduitSingle.next()) {
				int prod_id = getProduitSingle.getInt("prod_id");
				String prod_nom = getProduitSingle.getString("prod_nom");
				String prod_description = getProduitSingle.getString("prod_description");
				float prod_prix_vente_ttc = getProduitSingle.getFloat("prod_prix_vente_ttc");
				int prod_quantite_min = getProduitSingle.getInt("prod_quantite_min");
				float prod_quantite_stock = getProduitSingle.getFloat("prod_quantite_stock");
				String prod_unite = getProduitSingle.getString("prod_unite");
				String prod_statut = getProduitSingle.getString("prod_statut");
				int prod_cate_id = getProduitSingle.getInt("cate_id");
				p = new Produits(prod_id, prod_nom, prod_description, prod_prix_vente_ttc, prod_quantite_min, prod_quantite_stock, prod_unite, prod_statut, prod_cate_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static ResultSet getAllProduits()
	{
		ResultSet getAllProduits = null;

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les produits
			getAllProduits = st.executeQuery("SELECT " + 
					"	 prod_id AS Id," +
					"    prod_nom AS Nom," + 
					"    prod_prix_vente_ttc AS Prix," + 
					"    prod_quantite_min AS Quantité_min," + 
					"    prod_quantite_stock AS Stock," + 
					"    prod_unite AS Unité_Mesure," +
					"    prod_statut AS Statut " +
					"FROM" + 
					"    produits;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getAllProduits;
	}
	
	public static ResultSet getProduitsRupture()
	{
		ResultSet getProduitsRupture = null;

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les produits
			getProduitsRupture = st.executeQuery("SELECT " + 
					"	 prod_id AS Id," +
					"    prod_nom AS Nom," + 
					"    prod_prix_vente_ttc AS Prix," + 
					"    prod_quantite_min AS Quantité_min," + 
					"    prod_quantite_stock AS Stock," + 
					"    prod_unite AS Unité_Mesure," +
					"    prod_statut AS Statut " +
					"FROM" + 
					"    produits WHERE prod_statut = 'Rupture'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getProduitsRupture;
	}
	
	public static ResultSet getProduitsACommander()
	{
		ResultSet getProduitsACommander = null;

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les produits
			getProduitsACommander = st.executeQuery("SELECT " + 
					"	 prod_id AS Id," +
					"    prod_nom AS Nom," + 
					"    prod_prix_vente_ttc AS Prix," + 
					"    prod_quantite_min AS Quantité_min," + 
					"    prod_quantite_stock AS Stock," + 
					"    prod_unite AS Unité_Mesure," +
					"    prod_statut AS Statut " +
					"FROM" + 
					"    produits WHERE prod_quantite_stock < prod_quantite_min");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getProduitsACommander;
	}
	
	public static ResultSet getAllProduitByStatusResultSet(String... status) {
		ResultSet getProduitSingle = null;

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();
			
			// Préparer les arguments
			String statusArguments = "";
			for(int i = 0; i < status.length; i++) {
				statusArguments += "'" + status[i] + "'";
				if(i != status.length-1) {
					statusArguments += ",";
				}
			}

			// Recuperer et ajouter tous les produits
			getProduitSingle = st.executeQuery("SELECT " + 
					"	 prod_id AS Id," +
					"    prod_nom AS Nom," + 
					"    prod_prix_vente_ttc AS Prix," + 
					"    prod_quantite_min AS Quantité_min," + 
					"    prod_quantite_stock AS Stock," + 
					"    prod_unite AS Unité_Mesure," +
					"    prod_statut AS Statut " +
					"FROM" + 
					"    produits" + 
					"        WHERE" + 
					"    prod_statut in (" + statusArguments + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getProduitSingle;
	}
	
	public static void modifierStockEtPrixProduit(float montantModif, float prix,Produits produit)
	{
		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();
			
			String statut;
			
			if (produit.getProd_quantite_stock() + montantModif < produit.getProd_quantite_min())
			{
				statut = "A commander";
			}
			else if (produit.getProd_quantite_stock() + montantModif >= produit.getProd_quantite_min())
			{
				statut = "En stock";
			}
			else
			{
				statut = "Rupture";
			}

			st.executeUpdate("UPDATE produits SET prod_quantite_stock = prod_quantite_stock + " + montantModif + " WHERE prod_id = " + produit.getProd_id() + ";");
			st.executeUpdate("UPDATE produits SET prod_statut = '" + statut + "' WHERE prod_id = " + produit.getProd_id() + ";");
			st.executeUpdate("UPDATE produits SET prod_prix_vente_ttc = " + prix + "WHERE prod_id = " + produit.getProd_id() + ";");
			
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void modifierStockProduit(float montantModif,Produits produit)
	{
		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();
			
			String statut;
			
			if (produit.getProd_quantite_stock() + montantModif < produit.getProd_quantite_min())
			{
				statut = "A commander";
			}
			else if (produit.getProd_quantite_stock() + montantModif >= produit.getProd_quantite_min())
			{
				statut = "En stock";
			}
			else
			{
				statut = "Rupture";
			}

			st.executeUpdate("UPDATE produits SET prod_quantite_stock = prod_quantite_stock + " + montantModif + " WHERE prod_id = " + produit.getProd_id() + ";");
			st.executeUpdate("UPDATE produits SET prod_statut = '" + statut + "' WHERE prod_id = " + produit.getProd_id() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
