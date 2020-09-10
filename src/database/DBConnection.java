package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.ibatis.jdbc.ScriptRunner;

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
	}
}
