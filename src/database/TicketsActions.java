package database;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketsActions {
	public static boolean ajouterTicket(Timestamp date, String statut, String commentaire, int idUtilisateur) {
		try {
			Connection cnx = DBConnection.ConnectToDatabase();
			Statement st = cnx.createStatement();

			Tickets t = new Tickets(date, statut, commentaire, idUtilisateur);
			String insertStatement = t.createInsertStatement();

			st.execute(insertStatement);
			cnx.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ResultSet getTickets() {
		ResultSet resTickets = null;

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les tickets
			resTickets = st.executeQuery("SELECT " + 
					"    t.tckt_id AS Id," + 
					"    t.tckt_date AS Date," + 
					"    t.tckt_statut AS Statut," + 
					"    t.tckt_commentaire AS Commentaire," + 
					"    CONCAT(t.tckt_montant_ttc, ' EUR') AS Montant," + 
					"    CONCAT(u.utls_prenom, ' ', u.utls_nom) AS Utilisateur " + 
					"FROM" + 
					"    tickets t" + 
					"        JOIN" + 
					"    utilisateurs u ON t.utls_id = u.utls_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resTickets;
	}
}
