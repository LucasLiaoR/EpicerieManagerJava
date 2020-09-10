package MVC;

public class Utilisateur {
	private String nom;
	private String prenom;
	private String mdp;
	private int id;
	

	public Utilisateur(int identificateur, String n, String p, String pass)
	{
		this.id = identificateur;
		this.nom = n;
		this.prenom = p;
		this.mdp = pass;
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public String getMdp()
	{
		return mdp;
	}
	
	@Override
	public String toString()
	{
		return getID() + ", " + getNom() + ", " + getPrenom();
	}
}
