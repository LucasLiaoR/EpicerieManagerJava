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
}
