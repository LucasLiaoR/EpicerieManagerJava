package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilisateursActions {

	public static boolean ajouterUtilisateur(String nom, String prenom, String telephone, String mail, String adresse,
			String mdp, String caisse, int manager) {
		try {
			Connection cnx = DBConnection.ConnectToDatabase();
			Statement st = cnx.createStatement();

			Utilisateurs u = new Utilisateurs(nom, prenom, telephone, mail, adresse, mdp, caisse, manager);
			String insertStatement = u.createInsertStatement();

			st.execute(insertStatement);
			cnx.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<Utilisateurs> getUtilisateurs() {
		List<Utilisateurs> listUtilisateurs = new ArrayList<Utilisateurs>();

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les utilisateurs
			ResultSet resUtilisateurs = st.executeQuery("SELECT * FROM utilisateurs");
			while (resUtilisateurs.next()) {
				String nom = resUtilisateurs.getString("utls_nom");
				String prenom = resUtilisateurs.getString("utls_prenom");
				String telephone = resUtilisateurs.getString("utls_telephone");
				String mail = resUtilisateurs.getString("utls_mail");
				String adresse = resUtilisateurs.getString("utls_adresse");
				String mdp = resUtilisateurs.getString("utls_mdp");
				String caisse = resUtilisateurs.getString("utls_caisse");
				int manager = resUtilisateurs.getInt("utls_manager");
				Utilisateurs u = new Utilisateurs(nom, prenom, telephone, mail, adresse, mdp, caisse, manager);
				listUtilisateurs.add(u);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUtilisateurs;
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
				int prod_quantite_stock = resProduits.getInt("prod_quantite_stock");
				String prod_unite = resProduits.getString("prod_unite");
				String prod_statut = resProduits.getString("prod_statut");
				int prod_cate_id = resProduits.getInt("cate_id");
				
				
				Produits u = new Produits(prod_id, prod_nom, prod_description, prod_prix_vente_ttc, prod_quantite_min, prod_quantite_stock, prod_unite, prod_statut, prod_cate_id);
				listeProduits.add(u);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeProduits;
	}
	
	
	public static boolean ajouterProduit(int id, String nom, String desc, int prixVente, int qteMin, int qteStock, String unite, String statut, int cateID)
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
	
	public static List<Categories> getCategoriesDB()
	{
		List<Categories> listeCategories = new ArrayList<Categories>();
		
		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les produits
			ResultSet resProduits = st.executeQuery("SELECT * FROM categories");
			while (resProduits.next()) {
				int cate_id = resProduits.getInt("cate_id");
				String cate_libelle = resProduits.getString("cate_libelle");

				
				Categories c = new Categories(cate_id, cate_libelle);
				listeCategories.add(c);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCategories;
	}
	
}
