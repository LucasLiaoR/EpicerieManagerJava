package database;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Tickets implements Serializable {
	
	private static final long serialVersionUID = -1271951148360649142L;
	
	private int id;
	private Timestamp date;
	private String statut;
	private String commentaire;
	private int idUtilisateur;
	
	/**
	 * @param date
	 * @param statut
	 * @param commentaire
	 * @param id_utilisateur
	 */
	public Tickets(Timestamp date, String statut, String commentaire, int idUtilisateur) {
		this.date = date;
		this.statut = statut;
		this.commentaire = commentaire;
		this.idUtilisateur = idUtilisateur;
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public Date getDate() { return date; }

	public void setDate(Timestamp date) { this.date = date; }

	public String getStatut() { return statut; }

	public void setStatut(String statut) { this.statut = statut; }

	public String getCommentaire() { return commentaire; }

	public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

	public int getIdUtilisateur() { return idUtilisateur; }

	public void setId_utilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }
	
	public String createInsertStatement() {
		return "INSERT INTO TICKETS (tckt_date, tck_statut, tckt_commentaire, utls_id) VALUES("
				+ "NOW(),"
				+ "'" + this.getStatut() + "',"
				+ "'" + this.getCommentaire() + "',"
				+ "'" + this.getIdUtilisateur() + "'"
				+ ");";
	}
}
