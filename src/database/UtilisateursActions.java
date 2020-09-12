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
	
	public static ResultSet getUtilisateurSingle(int idUtilisateur) {
		ResultSet getUtilisateurSingle = null;

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les tickets
			getUtilisateurSingle = st.executeQuery("SELECT " + 
					"    utls_id AS ID," + 
					"    utls_nom AS Nom," + 
					"    utls_prenom AS Prenom," + 
					"    utls_telephone AS Telephone," + 
					"    utls_mail AS Mail," +
					"    utls_adresse AS Adresse," +
					"    utls_manager AS Manager " +
					"FROM" + 
					"    utilisateurs" + 
					"        WHERE" + 
					"    utls_id = " + idUtilisateur);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getUtilisateurSingle;
	}
	
	
	public static Utilisateurs authentificationUtilisateur(String pass, int id)
	{
		List<Utilisateurs> liste = new ArrayList<Utilisateurs>();
		
		liste = getUtilisateurs();
		
		
		for (Utilisateurs u : liste)
		{
			
			if (u.getMdp().toString().equals(pass) && u.getId() + 1 == id)
			{
				return u;
			}
		}

		return null;
		
	}
	
	
	
}
