package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TicketsProduitsActions {
	public static boolean ajouterTicketProduits(int ticketId, List<TicketProduitSingle> listProduitsIdsAvecQuantite) {
		try {
			Connection cnx = DBConnection.ConnectToDatabase();
			Statement st = cnx.createStatement();

			TicketsProduits tP = new TicketsProduits(ticketId, listProduitsIdsAvecQuantite);
			List<String> insertStatements = tP.createInsertStatements();

			for (String insertStatement : insertStatements) {
				st.execute(insertStatement);
			}
			
			for (TicketProduitSingle tps : listProduitsIdsAvecQuantite) {
				Produits p = ProduitsActions.getProduitSingleById(tps.produitId);
				ProduitsActions.modifierStockProduit(-tps.quantite, p);
			}

			cnx.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ResultSet getTicketProduitsByTicketId(String ticketId) {
		ResultSet resTicketsProduits = null;

		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les tickets
			resTicketsProduits = st.executeQuery("SELECT  " + 
					"    tp.prod_id AS 'Id'," + 
					"    p.prod_nom AS 'Nom'," + 
					"    tp.tckt_prod_quantite AS 'Quantite dans le ticket'," + 
					"    p.prod_unite AS 'Unite'," + 
					"    (p.prod_prix_vente_ttc * tp.tckt_prod_quantite) AS 'Montant TTC'" + 
					" FROM" + 
					"    tickets_produits tp" + 
					"        JOIN " + 
					"    produits p ON tp.prod_id = p.prod_id" + 
					" WHERE" + 
					"    tckt_id = " + ticketId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resTicketsProduits;
	}
}
