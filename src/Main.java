
import javax.swing.SwingUtilities;

import gui.*;
import database.*;

public class Main {

	public static void main(String[] args) {
		// Creer la BDD pour la 1ere fois
		DBConnection.CreateDatabase();
		
		// Creer les tables pour la 1ere fois
		try {
			DBConnection.CreateTables();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Lancer l'application
		SwingUtilities.invokeLater(new InterfaceUtilisateur()); 
	}

}
