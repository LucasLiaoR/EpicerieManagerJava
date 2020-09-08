package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;

public class InterfaceUtilisateur implements Runnable {

	private JFrame frame;
	private final JPanel TopPanel = new JPanel();
	private JTextField dateHeure;
	private JTextField headText;
	private JPanel LeftPanel;
	private JPanel panelGestionStock;
	private JLabel lblNewLabel;
	private JTextField AjoutProduit_NomProduit;
	private JTextField AjoutProduit_IDProduit;
	private JTextField AjoutProduit_Statut;
	private JTextField AjoutProduit_PrixVente;

	public void run() {
		try {
			InterfaceUtilisateur window = new InterfaceUtilisateur();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public InterfaceUtilisateur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1048, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		TopPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		TopPanel.setBounds(0, 0, 1032, 39);
		frame.getContentPane().add(TopPanel);
		TopPanel.setLayout(null);

		dateHeure = new JTextField();
		dateHeure.setEditable(false);
		dateHeure.setBounds(10, 11, 164, 18);
		TopPanel.add(dateHeure);
		dateHeure.setColumns(10);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date date = new Date();

		dateHeure.setText(date.toString());

		headText = new JTextField();
		headText.setEditable(false);
		headText.setFont(new Font("Tahoma", Font.BOLD, 11));
		headText.setText("Projet Java - Outil Caisse/Inventaire d'\u00E9picerie");
		headText.setColumns(10);
		headText.setBounds(433, 11, 275, 18);
		TopPanel.add(headText);

		LeftPanel = new JPanel();
		LeftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		LeftPanel.setBounds(0, 38, 182, 512);
		frame.getContentPane().add(LeftPanel);
		LeftPanel.setLayout(null);

		panelGestionStock = new JPanel();
		panelGestionStock.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelGestionStock.setBounds(10, 11, 162, 154);
		LeftPanel.add(panelGestionStock);
		panelGestionStock.setLayout(null);

		lblNewLabel = new JLabel("Gestion des stocks");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(0, 0, 162, 30);
		panelGestionStock.add(lblNewLabel);
		
		JButton btnAjouterProduit = new JButton("Ajouter un produit");
		btnAjouterProduit.setBounds(10, 41, 142, 30);
		panelGestionStock.add(btnAjouterProduit);
		
		JButton btnModifierQuantite = new JButton("Modifier quantit\u00E9 stock");
		btnModifierQuantite.setBounds(10, 76, 142, 31);
		panelGestionStock.add(btnModifierQuantite);
		
		JPanel panelGestionCaisse = new JPanel();
		panelGestionCaisse.setLayout(null);
		panelGestionCaisse.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelGestionCaisse.setBounds(10, 176, 162, 154);
		LeftPanel.add(panelGestionCaisse);
		
		JLabel lblVentes = new JLabel("Gestion Caisse");
		lblVentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentes.setForeground(Color.BLACK);
		lblVentes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVentes.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblVentes.setBackground(SystemColor.activeCaption);
		lblVentes.setBounds(0, 0, 162, 30);
		panelGestionCaisse.add(lblVentes);
		
		JButton btnCreerNewTicket = new JButton("Cr\u00E9er nouveau ticket");
		btnCreerNewTicket.setBounds(10, 41, 142, 30);
		panelGestionCaisse.add(btnCreerNewTicket);
		
		JPanel panelGestionClientele = new JPanel();
		panelGestionClientele.setLayout(null);
		panelGestionClientele.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelGestionClientele.setBounds(10, 341, 162, 154);
		LeftPanel.add(panelGestionClientele);
		
		JLabel lblGestionFichierClient = new JLabel("Gestion Fichier Client");
		lblGestionFichierClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionFichierClient.setForeground(Color.BLACK);
		lblGestionFichierClient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGestionFichierClient.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblGestionFichierClient.setBackground(SystemColor.activeCaption);
		lblGestionFichierClient.setBounds(0, 0, 162, 30);
		panelGestionClientele.add(lblGestionFichierClient);
		
		JButton btnParcourirFichierClient = new JButton("Parcourir fichier client");
		btnParcourirFichierClient.setBounds(10, 41, 142, 30);
		panelGestionClientele.add(btnParcourirFichierClient);
		
		JButton btnAjouterUnNouveau = new JButton("Ajouter nouveau client");
		btnAjouterUnNouveau.setBounds(10, 77, 142, 30);
		panelGestionClientele.add(btnAjouterUnNouveau);
		
		JPanel panelAjouterProduit = new JPanel();
		panelAjouterProduit.setBounds(181, 38, 851, 512);
		frame.getContentPane().add(panelAjouterProduit);
		panelAjouterProduit.setLayout(null);
		
		AjoutProduit_NomProduit = new JTextField();
		AjoutProduit_NomProduit.setBounds(200, 103, 591, 27);
		panelAjouterProduit.add(AjoutProduit_NomProduit);
		AjoutProduit_NomProduit.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom du produit");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 102, 163, 27);
		panelAjouterProduit.add(lblNewLabel_1);
		
		JLabel lblIdProduit = new JLabel("ID produit");
		lblIdProduit.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdProduit.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdProduit.setBounds(10, 140, 163, 27);
		panelAjouterProduit.add(lblIdProduit);
		
		AjoutProduit_IDProduit = new JTextField();
		AjoutProduit_IDProduit.setColumns(10);
		AjoutProduit_IDProduit.setBounds(200, 141, 591, 27);
		panelAjouterProduit.add(AjoutProduit_IDProduit);
		
		JLabel lblNewLabel_2 = new JLabel("Ajout d'un nouveau produit");
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(325, 26, 239, 48);
		panelAjouterProduit.add(lblNewLabel_2);
		
		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie");
		lblCatgorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatgorie.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCatgorie.setBounds(10, 178, 163, 27);
		panelAjouterProduit.add(lblCatgorie);
		
		JList AjoutProduit_ListeCategorie = new JList();
		AjoutProduit_ListeCategorie.setBounds(200, 179, 591, 26);
		panelAjouterProduit.add(AjoutProduit_ListeCategorie);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatut.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStatut.setBounds(10, 216, 163, 27);
		panelAjouterProduit.add(lblStatut);
		
		AjoutProduit_Statut = new JTextField();
		AjoutProduit_Statut.setColumns(10);
		AjoutProduit_Statut.setBounds(200, 216, 591, 27);
		panelAjouterProduit.add(AjoutProduit_Statut);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescription.setBounds(10, 254, 163, 27);
		panelAjouterProduit.add(lblDescription);
		
		JTextArea AjoutProduit_Desc = new JTextArea();
		AjoutProduit_Desc.setBounds(200, 254, 591, 102);
		panelAjouterProduit.add(AjoutProduit_Desc);
		
		JLabel lblPrixDeVente = new JLabel("Prix de vente");
		lblPrixDeVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrixDeVente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrixDeVente.setBounds(10, 394, 163, 27);
		panelAjouterProduit.add(lblPrixDeVente);
		
		AjoutProduit_PrixVente = new JTextField();
		AjoutProduit_PrixVente.setBounds(200, 392, 591, 32);
		panelAjouterProduit.add(AjoutProduit_PrixVente);
		AjoutProduit_PrixVente.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter le produit");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(553, 437, 271, 52);
		panelAjouterProduit.add(btnNewButton);
	}
}
