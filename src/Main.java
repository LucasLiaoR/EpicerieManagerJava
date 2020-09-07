
import javax.swing.SwingUtilities;

import gui.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new InterfaceUtilisateur()); // Un appel qui permet de lancer de façon sûre l'interface graphique créé dans la méthode run() de l'objet
	}

}
