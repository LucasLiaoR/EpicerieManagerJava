package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import database.Utilisateurs;
import database.DBConnection;
import database.UtilisateursActions;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class SelectUserFrame implements Runnable {

	private JFrame frmSelectUser;
	private JTextField fieldNom;
	private JTextField fieldPrenom;
	private JPasswordField passwordField;
	private Utilisateurs user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectUserFrame window = new SelectUserFrame();
					window.frmSelectUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectUserFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public static List<Utilisateurs> listeUserDB = new ArrayList<Utilisateurs>();
	
	private void initialize() {
		frmSelectUser = new JFrame();
		frmSelectUser.setTitle("Epicerie Manager");
		frmSelectUser.setBounds(100, 100, 1048, 589);
		frmSelectUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectUser.getContentPane().setLayout(null);
		
		
		
		JButton btnNewButton = new JButton("Choisir cet utilisateur");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean matchConnexion = false;
				
				listeUserDB = UtilisateursActions.getUtilisateurs();

				for (Utilisateurs u : listeUserDB)
				{
					if (fieldNom.getText().toString().equals(u.getNom()) && fieldPrenom.getText().toString().equals(u.getPrenom()) && String.copyValueOf(passwordField.getPassword()).toString().equals(u.getMdp()))
					{
						matchConnexion = true;
						setUser(u);
					}
				}
				
				

				if (matchConnexion)
				{
					frmSelectUser.setVisible(false);
					SwingUtilities.invokeLater(new InterfaceUtilisateur(user)); 
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Les identifiants renseignés ne correspondent à aucun utilisateur connu", "Erreur !", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(801, 456, 221, 83);
		frmSelectUser.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Choisir un utilisateur pour cette caisse");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(381, 11, 307, 57);
		frmSelectUser.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom utilisateur");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(51, 149, 143, 39);
		frmSelectUser.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPrnomUtilisateur = new JLabel("Pr\u00E9nom utilisateur");
		lblPrnomUtilisateur.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrnomUtilisateur.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrnomUtilisateur.setBounds(51, 225, 143, 39);
		frmSelectUser.getContentPane().add(lblPrnomUtilisateur);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMotDePasse.setBounds(51, 299, 143, 39);
		frmSelectUser.getContentPane().add(lblMotDePasse);
		
		fieldNom = new JTextField();
		fieldNom.setBounds(289, 149, 447, 39);
		frmSelectUser.getContentPane().add(fieldNom);
		fieldNom.setColumns(10);
		
		fieldPrenom = new JTextField();
		fieldPrenom.setColumns(10);
		fieldPrenom.setBounds(289, 225, 447, 39);
		frmSelectUser.getContentPane().add(fieldPrenom);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(289, 299, 447, 37);
		frmSelectUser.getContentPane().add(passwordField);
	}

	@Override
	public void run() {
		try {
			SelectUserFrame window = new SelectUserFrame();
			window.frmSelectUser.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setUser(Utilisateurs u)
	{
		this.user = u;
	}
	
	public Utilisateurs getUser()
	{
		return this.user;
	}

	
}
