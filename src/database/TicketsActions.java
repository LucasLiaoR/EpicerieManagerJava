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

	public static List<Tickets> getTickets() {
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
				int idUtilisateur = resTickets.getInt("utls_id");
				Tickets t = new Tickets(date, statut, commentaire, idUtilisateur);
				listTickets.add(t);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTickets;
	}
}
