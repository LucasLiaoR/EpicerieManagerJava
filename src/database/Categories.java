package database;

import java.io.Serializable;

public class Categories implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7184213973992592320L;

	
	private int cate_id;
	private String cate_libelle;
	
	
	public Categories(int id, String lib)
	{
		this.cate_id = id;
		this.cate_libelle = lib;
	}
	
	public int getID()
	{
		return this.cate_id;
	}
	
	public String getLibelle()
	{
		return this.cate_libelle;
	}
	
}
