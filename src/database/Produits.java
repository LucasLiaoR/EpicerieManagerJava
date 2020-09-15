package database;

import java.io.Serializable;

public class Produits implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1898737141129870323L;

	private int prod_id;
	private String prod_nom;
	private String prod_description;
	private float prod_prix_vente_ttc;
	private int prod_quantite_min;
	private float prod_quantite_stock;
	private String prod_unite;
	private String prod_statut;
	private int cate_id;
	
	public String createInsertStatement() {
		return "INSERT INTO PRODUITS (prod_nom, prod_description, prod_prix_vente_ttc, prod_quantite_min, prod_quantite_stock, prod_unite, prod_statut, cate_id) VALUES("
				+ "'" + this .getProd_nom() + "',"
				+ "'" + this .getProd_description() + "',"
				+ "" + this .getProd_prix_vente_ttc() + ","
				+ "" + this .getProd_quantite_min() + ","
				+ "" + this .getProd_quantite_stock() + ","
				+ "'" + this .getProd_unite() + "',"
				+ "'" + this .getProd_statut() + "',"
				+ "" + this .getCate_id() + ""
				+ ");";
	}
	
	public Produits(int id, String nom, String desc, float prixVente, int qteMin, float qteStock, String unite, String statut, int cateID)
	{
		this.prod_id = id;
		this.prod_nom = nom;
		this.prod_description = desc;
		this.prod_prix_vente_ttc = prixVente;
		this.prod_quantite_min = qteMin;
		this.prod_quantite_stock = qteStock;
		this.prod_unite = unite;
		this.prod_statut = statut;
		this.cate_id = cateID;
	}
	
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_nom() {
		return prod_nom;
	}
	public void setProd_nom(String prod_nom) {
		this.prod_nom = prod_nom;
	}
	public float getProd_prix_vente_ttc() {
		return prod_prix_vente_ttc;
	}
	public void setProd_prix_vente_ttc(int prod_prix_vente_ttc) {
		this.prod_prix_vente_ttc = prod_prix_vente_ttc;
	}
	public float getProd_quantite_stock() {
		return prod_quantite_stock;
	}
	public void setProd_quantite_stock(int prod_quantite_stock) {
		this.prod_quantite_stock = prod_quantite_stock;
	}
	public String getProd_description() {
		return prod_description;
	}
	public void setProd_description(String prod_description) {
		this.prod_description = prod_description;
	}
	public int getProd_quantite_min() {
		return prod_quantite_min;
	}
	public void setProd_quantite_min(int prod_quantite_min) {
		this.prod_quantite_min = prod_quantite_min;
	}
	public String getProd_unite() {
		return prod_unite;
	}
	public void setProd_unite(String prod_unite) {
		this.prod_unite = prod_unite;
	}
	public String getProd_statut() {
		return prod_statut;
	}
	public void setProd_statut(String prod_statut) {
		this.prod_statut = prod_statut;
	}
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	
	
	
	
	
}
