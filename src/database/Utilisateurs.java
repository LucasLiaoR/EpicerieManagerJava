package database;

import java.io.Serializable;

public class Utilisateurs implements Serializable {

	private static final long serialVersionUID = 4253843746825925465L;
	
	private int id;
	private String nom;
	private String prenom;
	private String telephone;
	private String mail;
	private String adresse;
	private String mdp;
	private String caisse;
	private int manager = 0;

	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param mail
	 * @param adresse
	 * @param mdp
	 * @param caisse
	 * @param manager
	 */
	public Utilisateurs(String nom, String prenom, String telephone, String mail, String adresse, String mdp,
			String caisse, int manager) {
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.adresse = adresse;
		this.mdp = mdp;
		this.caisse = caisse;
		this.manager = manager;
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getNom() { return nom; }

	public void setNom(String nom) { this.nom = nom; }

	public String getPrenom() { return prenom; }

	public void setPrenom(String prenom) { this.prenom = prenom; }

	public String getTelephone() { return telephone; }

	public void setTelephone(String telephone) { this.telephone = telephone; }

	public String getMail() { return mail; }

	public void setMail(String mail) { this.mail = mail; }

	public String getAdresse() { return adresse; }

	public void setAdresse(String adresse) { this.adresse = adresse; }

	public String getMdp() { return mdp; }

	public void setMdp(String mdp) { this.mdp = mdp; }

	public String getCaisse() { return caisse; }

	public void setCaisse(String caisse) { this.caisse = caisse; }

	public int isManager() { return manager; }

	public void setManager(int manager) { this.manager = manager; }
	
	public String createInsertStatement() {
		return "INSERT INTO UTILISATEURS (utls_nom, utls_prenom, utls_telephone, utls_mail, utls_adresse, utls_mdp, utls_caisse, utls_manager) VALUES("
				+ "'" + this.getNom() + "',"
				+ "'" + this.getPrenom() + "',"
				+ "'" + this.getTelephone() + "',"
				+ "'" + this.getMail() + "',"
				+ "'" + this.getAdresse() + "',"
				+ "'" + this.getMdp() + "',"
				+ "'" + this.getCaisse() + "',"
				+ "'" + this.isManager() + "'"
				+ ");";
	}
}
