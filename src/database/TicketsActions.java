package database;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketsActions {
	public static boolean ajouterTicket(String statut, String commentaire, float montant, int idUtilisateur) {
		try {
			Connection cnx = DBConnection.ConnectToDatabase();
			Statement st = cnx.createStatement();

			Tickets t = new Tickets(statut, commentaire, montant, idUtilisateur);
			String insertStatement = t.createInsertStatement();

			st.execute(insertStatement);
			cnx.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ResultSet getTicketsResultSet() {
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
	
	public static List<Tickets> getTicketsList() {
		List<Tickets> listTickets = new ArrayList<Tickets>();

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les tickets
			ResultSet resTickets = st.executeQuery("SELECT * FROM tickets");
			while (resTickets.next()) {
				Timestamp date = resTickets.getTimestamp("tckt_date");
				String statut = resTickets.getString("tck_statut");
				String commentaire = resTickets.getString("tckt_commentaire");
				float montant = resTickets.getFloat("tckt_commentaire");
				int idUtilisateur = resTickets.getInt("utls_id");
				Tickets t = new Tickets(date, statut, commentaire, montant, idUtilisateur);
				listTickets.add(t);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTickets;
	}
	
	public static int getLastInsertIdTicket() {
		int lastInsertId = -1;
		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			ResultSet resTickets = st.executeQuery("SELECT MAX(tckt_id) AS MAX_ID FROM tickets;");
			if(resTickets.next()) {
				lastInsertId = resTickets.getInt("MAX_ID");
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastInsertId;
	}
	
	public static int getNextInsertIdTicket() {
		return getLastInsertIdTicket() + 1;
	}
}
