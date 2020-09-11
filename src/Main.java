
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Les tables existent d�j�");
		} catch (Exception e) {
			System.out.println("Les tables existent d�j�");
		}
		
		// Lancer l'application 
		SwingUtilities.invokeLater(new SelectUserFrame()); 
		//SwingUtilities.invokeLater(new InterfaceUtilisateur()); 
	}

}
