package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.ScriptRunner;

import MVC.Utilisateur;

public class DBConnection {

	private final static String DATABASE_NAME = "epicerie_manager";
	private final static String DATABASE_USER = "root";
	private final static String DATABASE_PASSWORD = "";

	public static void CreateDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/", DATABASE_USER, DATABASE_PASSWORD);
			Statement st = cnx.createStatement();
			st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME + " CHARACTER SET utf8 COLLATE utf8_general_ci;");
			cnx.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection ConnectToDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cnx = DriverManager.getConnection(
					"jdbc:mysql://localhost/" + DATABASE_NAME + "?serverTimezone=UTC", DATABASE_USER,
					DATABASE_PASSWORD);
			return cnx;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void CreateTables() throws Exception {
		// Init la connection
		Connection cnx = ConnectToDatabase();

		// Init le ScriptRunner
		ScriptRunner sr = new ScriptRunner(cnx);

		// Creer l'objet Reader
		Reader reader = new BufferedReader(new FileReader("conception/epicerie_bdd.sql"));

		// Executer le script
		sr.runScript(reader);
		
		cnx.close();
	}
	
	public static ArrayList<Utilisateur> getListeUserDatabase(ArrayList<Utilisateur> listUser)
	{
		Connection cnx = ConnectToDatabase();
		
		try {
			Statement st = cnx.createStatement();
			
			ResultSet r = st.executeQuery("SELECT * FROM Utilisateurs");
			
			while (r.next())
			{
				int id = r.getInt("utls_id");
				String nom = r.getString("utls_nom");
				String prenom = r.getString("utls_prenom");
				String mdp = r.getString("utls_mdp");
				
				Utilisateur user = new Utilisateur(id, nom, prenom, mdp);
				listUser.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listUser;
	}
}
