package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import database.Utilisateurs;
import database.UtilisateursActions;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InterfaceUtilisateur implements Runnable {

	private static JFrame frmAjoutDunNouveau;
	private final JPanel TopPanel = new JPanel();
	private JTextField dateHeure;
	private JTextField headText;
	private JPanel LeftPanel;
	private JPanel panelGestionStock;
	private JLabel lblNewLabel;
	private JLayeredPane PanelsStack;
	private JPanel panelAjouterProduit;
	private JPanel panelModifierQuantiteStock;
	private JPanel panelCreerNouveauTicket;
	private JPanel panelParcourirFichierClient;
	private JPanel panelAjouterNouveauClient;
	private JPanel panelAffichageTickets;
	public static JLabel labelUtilisateurCo;
	private static Utilisateurs user;
	
	public void run() {
		try {
			InterfaceUtilisateur window = new InterfaceUtilisateur(user);
			InterfaceUtilisateur.frmAjoutDunNouveau.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public InterfaceUtilisateur(Utilisateurs u) {
		InterfaceUtilisateur.user = u;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void ticktock()
	{
		dateHeure.setText(DateFormat.getDateTimeInstance().format(new Date()));
	}
	
	private void initialize() {
		frmAjoutDunNouveau = new JFrame();
		frmAjoutDunNouveau.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				panelCreerNouveauTicket = new CreationNouveauTicketPanel().getPanel(); // Afficher toujours le panel panelCreerNouveauTicket en premier
				switchPanels(panelCreerNouveauTicket);
			}
		});
		frmAjoutDunNouveau.setTitle("Epicerie Manager");
		frmAjoutDunNouveau.setBounds(100, 100, 1048, 589);
		frmAjoutDunNouveau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjoutDunNouveau.getContentPane().setLayout(null);
		TopPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		TopPanel.setBounds(0, 0, 1032, 39);
		frmAjoutDunNouveau.getContentPane().add(TopPanel);
		TopPanel.setLayout(null);

		dateHeure = new JTextField();
		dateHeure.setHorizontalAlignment(SwingConstants.CENTER);
		dateHeure.setEditable(false);
		dateHeure.setBounds(10, 11, 164, 18);
		TopPanel.add(dateHeure);
		dateHeure.setColumns(10);
		
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ticktock();
			}
		});
		
		timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();

		headText = new JTextField();
		headText.setEditable(false);
		headText.setFont(new Font("Tahoma", Font.BOLD, 11));
		headText.setText("Projet Java - Outil Caisse/Inventaire d'\u00E9picerie");
		headText.setColumns(10);
		headText.setBounds(257, 11, 275, 18);
		TopPanel.add(headText);
		
		labelUtilisateurCo = new JLabel("Utilisateur connect\u00E9 : ");
		labelUtilisateurCo.setHorizontalAlignment(SwingConstants.CENTER);
		labelUtilisateurCo.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelUtilisateurCo.setBounds(757, 0, 275, 39);
		labelUtilisateurCo.setText("Utilisateur connecté : " + getUser().getPrenom() + " - " + getUser().getNom());
		TopPanel.add(labelUtilisateurCo);
		
		JLabel lblNumroDeCaisse = new JLabel("Num\u00E9ro de caisse :");
		lblNumroDeCaisse.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumroDeCaisse.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNumroDeCaisse.setBounds(624, 0, 134, 39);
		lblNumroDeCaisse.setText("Num\u00E9ro de caisse : 0");
		TopPanel.add(lblNumroDeCaisse);
		
		PanelsStack = new JLayeredPane();
		PanelsStack.setBounds(181, 38, 851, 512);
		frmAjoutDunNouveau.getContentPane().add(PanelsStack);
		PanelsStack.setLayout(new CardLayout(0, 0));
		
		panelCreerNouveauTicket = new CreationNouveauTicketPanel().getPanel();
		PanelsStack.add(panelCreerNouveauTicket, "name_6306682648400");
		
		panelAjouterProduit = new AjoutProduitPanel().getPanel();
		PanelsStack.add(panelAjouterProduit, "name_6306658276400");
		
		panelModifierQuantiteStock = new ModificationQuantiteStockPanel().getPanel();
		PanelsStack.add(panelModifierQuantiteStock, "name_6306670600200");
		
		panelParcourirFichierClient = new AffichageFichierUtilisateurPanel().getPanel();
		PanelsStack.add(panelParcourirFichierClient, "name_6306694792400");
		
		panelAjouterNouveauClient = new AjoutUtilisateurPanel().getPanel();
		PanelsStack.add(panelAjouterNouveauClient, "name_6306706746800");
		
		panelAffichageTickets = new AffichageTicketsPanel().getPanel();
		PanelsStack.add(panelAffichageTickets, "name_20462142220600");

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
		
		JButton btnAjouterProduit = new JButton("Ajouter produit");
		btnAjouterProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelAjouterProduit);
			}
		});
		btnAjouterProduit.setMargin(new Insets(2, 2, 2, 2));
		btnAjouterProduit.setBounds(10, 41, 142, 30);
		panelGestionStock.add(btnAjouterProduit);
		
		JButton btnModifierQuantite = new JButton("Modifier quantit\u00E9 stock");
		btnModifierQuantite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelModifierQuantiteStock);
			}
		});
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
		btnCreerNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelCreerNouveauTicket);
			}
		});
		btnCreerNewTicket.setMargin(new Insets(2, 2, 2, 2));
		btnCreerNewTicket.setBounds(10, 41, 142, 30);
		panelGestionCaisse.add(btnCreerNewTicket);
		
		JButton btnHistoriqueDeTransaction = new JButton("Historique de transaction");
		btnHistoriqueDeTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAffichageTickets = new AffichageTicketsPanel().getPanel(); // Rafraichir l'historique de transaction chaque fois pour avoir les nouveaux tickets
				switchPanels(panelAffichageTickets);
			}
		});
		btnHistoriqueDeTransaction.setMargin(new Insets(0, 0, 0, 0));
		btnHistoriqueDeTransaction.setBounds(2, 83, 158, 30);
		panelGestionCaisse.add(btnHistoriqueDeTransaction);
		
		JPanel panelGestionClientele = new JPanel();
		panelGestionClientele.setLayout(null);
		panelGestionClientele.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelGestionClientele.setBounds(10, 341, 162, 154);
		LeftPanel.add(panelGestionClientele);
		
		JLabel lblGestionFichierClient = new JLabel("Gestion Utilisateur");
		lblGestionFichierClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionFichierClient.setForeground(Color.BLACK);
		lblGestionFichierClient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGestionFichierClient.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblGestionFichierClient.setBackground(SystemColor.activeCaption);
		lblGestionFichierClient.setBounds(0, 0, 162, 30);
		panelGestionClientele.add(lblGestionFichierClient);
		
		JButton btnParcourirFichierClient = new JButton("Changer d'utilisateur");
		btnParcourirFichierClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelParcourirFichierClient);
			}
		});
		btnParcourirFichierClient.setMargin(new Insets(2, 2, 2, 2));
		btnParcourirFichierClient.setBounds(10, 41, 142, 30);
		panelGestionClientele.add(btnParcourirFichierClient);
		
		JButton btnAjouterUnNouveau = new JButton("Ajouter utilisateur");
		btnAjouterUnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.isManager() == 1)
				{
					switchPanels(panelAjouterNouveauClient);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Il faut être manager pour accéder à cette option !", "Erreur !", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnAjouterUnNouveau.setMargin(new Insets(2, 2, 2, 2));
		btnAjouterUnNouveau.setBounds(10, 77, 142, 30);
		panelGestionClientele.add(btnAjouterUnNouveau);
		

	}
	
	public void switchPanels(JPanel panel) {
		PanelsStack.removeAll();
		PanelsStack.add(panel);
		PanelsStack.repaint();
		PanelsStack.revalidate();
	}
	
	public static Utilisateurs getUser()
	{
		return user;
	}
	
	public static void setUser(Utilisateurs u)
	{
		user = u;
	}
	
	public static void setIdUser()
	{
		int id = UtilisateursActions.recupID(user.getNom(), user.getPrenom());
		
		user.setId(id);
	}
	
	public static void setLabelUser()
	{
		labelUtilisateurCo.setText("Utilisateur connecté : " + getUser().getPrenom() + " - " + getUser().getNom());
	}
	
	public static void setVisible (boolean isVisible) {
		frmAjoutDunNouveau.setVisible(isVisible);
	}
	
	public static boolean isVisible() {
		return frmAjoutDunNouveau.isVisible();
	}
}
