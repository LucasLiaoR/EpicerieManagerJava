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
import java.awt.Insets;

public class InterfaceUtilisateur implements Runnable {

	private JFrame frmAjoutDunNouveau;
	private final JPanel TopPanel = new JPanel();
	private JTextField dateHeure;
	private JTextField headText;
	private JPanel LeftPanel;
	private JPanel panelGestionStock;
	private JLabel lblNewLabel;

	public void run() {
		try {
			InterfaceUtilisateur window = new InterfaceUtilisateur();
			window.frmAjoutDunNouveau.setVisible(true);
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
		frmAjoutDunNouveau = new JFrame();
		frmAjoutDunNouveau.setTitle("Epicerie Manager");
		frmAjoutDunNouveau.setBounds(100, 100, 1048, 589);
		frmAjoutDunNouveau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjoutDunNouveau.getContentPane().setLayout(null);
		TopPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		TopPanel.setBounds(0, 0, 1032, 39);
		frmAjoutDunNouveau.getContentPane().add(TopPanel);
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
		frmAjoutDunNouveau.getContentPane().add(LeftPanel);
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
		btnAjouterProduit.setMargin(new Insets(2, 2, 2, 2));
		btnAjouterProduit.setBounds(10, 41, 142, 30);
		panelGestionStock.add(btnAjouterProduit);
		
		JButton btnModifierQuantite = new JButton("Modifier quantit\u00E9 stock");
		btnModifierQuantite.setMargin(new Insets(2, 2, 2, 2));
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
		btnCreerNewTicket.setMargin(new Insets(2, 2, 2, 2));
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
		btnParcourirFichierClient.setMargin(new Insets(2, 2, 2, 2));
		btnParcourirFichierClient.setBounds(10, 41, 142, 30);
		panelGestionClientele.add(btnParcourirFichierClient);
		
		JButton btnAjouterUnNouveau = new JButton("Ajouter nouveau client");
		btnAjouterUnNouveau.setMargin(new Insets(2, 2, 2, 2));
		btnAjouterUnNouveau.setBounds(10, 77, 142, 30);
		panelGestionClientele.add(btnAjouterUnNouveau);
		
		JPanel panelAjouterProduit = new AjoutProduitPanel().getPanel();
		frmAjoutDunNouveau.getContentPane().add(panelAjouterProduit);
	}
}
