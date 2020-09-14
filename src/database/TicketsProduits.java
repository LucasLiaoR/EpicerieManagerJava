package database;

import java.util.ArrayList;
import java.util.List;

public class TicketsProduits {
	
	private int ticketId;
	private List<TicketProduitSingle> listProduitsIdsAvecQuantite;
	
	/**
	 * @param ticketId
	 * @param listProduitsIdsAvecQuantite
	 * @param quantite
	 */
	protected TicketsProduits(int ticketId, List<TicketProduitSingle> listProduitsIdsAvecQuantite) {
		this.ticketId = ticketId;
		this.listProduitsIdsAvecQuantite = listProduitsIdsAvecQuantite;
	}

	public int getTicketId() { return ticketId; }

	public void setTicketId(int ticketId) { this.ticketId = ticketId; }

	public List<TicketProduitSingle> getListProduitsIdsAvecQuantite() { return listProduitsIdsAvecQuantite; }

	public void setListProduitsIdsAvecQuantite(List<TicketProduitSingle> listProduitsIdsAvecQuantite) { this.listProduitsIdsAvecQuantite = listProduitsIdsAvecQuantite; }

	public List<String> createInsertStatements() {
		List<String> insertStatements = new ArrayList<String>();
		for(TicketProduitSingle ticketProduit : this.getListProduitsIdsAvecQuantite()) {
			String insertStatement = "INSERT INTO tickets_produits (tckt_id, prod_id, tckt_prod_quantite) VALUES ("
					+ "'" + this.getTicketId() +"',"
					+ "'" + ticketProduit.produitId +"',"
					+ "'" + ticketProduit.quantite +"'"
					+ ");";
			insertStatements.add(insertStatement);
		}
		return insertStatements;
	}
}
