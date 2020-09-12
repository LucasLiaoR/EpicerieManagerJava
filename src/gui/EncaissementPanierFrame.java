package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import database.ProduitsActions;
import database.Tickets;
import database.TicketsActions;
import database.Utilisateurs;
import fr.sql.utilities.DbUtils;
import fr.sql.utilities.MyTableModel;

import java.awt.SystemColor;
import javax.swing.Icon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EncaissementPanierFrame implements Runnable {
	
	private JFrame encaissementPanierFrame;
	private List<Integer> indexProduitsDansPanier;
	private MyTableModel tableProduitsData;
	private float montantPanier;
	private float montantAPayer;
	private float montantARendre;
	private Utilisateurs user;
	private JPanel TopPanel;
	private JTextField dateHeure;
	private JTextField headText;
	private JLabel lblEur;
	private JLayeredPane layeredPane;
	private JPanel panel_PaiementEnEspeces;
	private JPanel panel_PaiementParCarte;
	private JPasswordField codePin;
	private JComboBox comboBox;
	private JButton btnEncaisser;
	private JLabel label_MontantAPayer;
	private JLabel label_MontantARendre;
	
	public void run() {
		try {
			EncaissementPanierFrame window = new EncaissementPanierFrame(indexProduitsDansPanier, tableProduitsData, montantPanier, user);
			window.encaissementPanierFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public EncaissementPanierFrame(List<Integer> list, MyTableModel tableData, float montant, Utilisateurs u) {
		this.user = u;
		this.indexProduitsDansPanier = list;
		this.tableProduitsData = tableData;
		this.montantPanier = montant;
		montantAPayer = this.montantPanier;
		montantARendre = 0;
		initialize();
	}
	
	public void ticktock()
	{
		dateHeure.setText(DateFormat.getDateTimeInstance().format(new Date()));
	}

	private void initialize() {
		encaissementPanierFrame = new JFrame();
		encaissementPanierFrame.setTitle("Epicerie Manager - Encaissement");
		encaissementPanierFrame.setBounds(100, 100, 1048, 589);
		encaissementPanierFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		encaissementPanierFrame.getContentPane().setLayout(null);
		TopPanel = new JPanel();
		TopPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		TopPanel.setBounds(0, 0, 1032, 39);
		encaissementPanierFrame.getContentPane().add(TopPanel);
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
		
		JLabel labelUtilisateurCo = new JLabel("Utilisateur connect\u00E9 : ");
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 63, 1012, 476);
		encaissementPanierFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(0, 0, 353, 476);
		panel.add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(this.tableProduitsData);
		
		JLabel lblNewLabel = new JLabel("Montant total");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(363, 0, 121, 34);
		panel.add(lblNewLabel);
		
		lblEur = new JLabel(this.montantPanier + " EUR    ");
		lblEur.setOpaque(true);
		lblEur.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEur.setBackground(SystemColor.info);
		lblEur.setBounds(363, 30, 161, 25);
		panel.add(lblEur);
		
		BufferedImage billet5EurosImage = null;
		BufferedImage billet10EurosImage = null;
		BufferedImage billet20EurosImage = null;
		BufferedImage billet50EurosImage = null;
		BufferedImage billet100EurosImage = null;
		BufferedImage piece1CentimesImage = null;
		BufferedImage piece2CentimesImage = null;
		BufferedImage piece5CentimesImage = null;
		BufferedImage piece10CentimesImage = null;
		BufferedImage piece20CentimesImage = null;
		BufferedImage piece50CentimesImage = null;
		BufferedImage piece1EuroImage = null;
		BufferedImage piece2EuroImage = null;
		try {
			billet5EurosImage = ImageIO.read(new File("images/5_euros.jpg"));
			billet10EurosImage = ImageIO.read(new File("images/10_euros.jpg"));
			billet20EurosImage = ImageIO.read(new File("images/20_euros.jpg"));
			billet50EurosImage = ImageIO.read(new File("images/50_euros.jpg"));
			billet100EurosImage = ImageIO.read(new File("images/100_euros.jpg"));
			piece1CentimesImage = ImageIO.read(new File("images/1_centime.png"));
			piece2CentimesImage = ImageIO.read(new File("images/2_centime.png"));
			piece5CentimesImage = ImageIO.read(new File("images/5_centime.png"));
			piece10CentimesImage = ImageIO.read(new File("images/10_centime.png"));
			piece20CentimesImage = ImageIO.read(new File("images/20_centime.png"));
			piece50CentimesImage = ImageIO.read(new File("images/50_centime.png"));
			piece1EuroImage = ImageIO.read(new File("images/1_euro.png"));
			piece2EuroImage = ImageIO.read(new File("images/2_euros.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		};
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(363, 98, 649, 342);
		panel.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panel_PaiementEnEspeces = new JPanel();
		layeredPane.add(panel_PaiementEnEspeces, "name_134224807720499");
		panel_PaiementEnEspeces.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 123, 649, 219);
		panel_PaiementEnEspeces.add(panel_2);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setLayout(null);
		
		JLabel label_1Centime = new JLabel(new ImageIcon(piece1CentimesImage.getScaledInstance(80, 80, Image.SCALE_FAST)));
		label_1Centime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(0.01f);
			}
		});
		label_1Centime.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1Centime.setBounds(0, 0, 80, 80);
		panel_2.add(label_1Centime);
		
		JLabel label_2Centimes = new JLabel(new ImageIcon(piece2CentimesImage.getScaledInstance(80, 80, Image.SCALE_FAST)));
		label_2Centimes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(0.02f);
			}
		});
		label_2Centimes.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2Centimes.setBounds(90, 0, 80, 80);
		panel_2.add(label_2Centimes);
		
		JLabel label_5Centimes = new JLabel(new ImageIcon(piece5CentimesImage.getScaledInstance(80, 80, Image.SCALE_FAST)));
		label_5Centimes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(0.05f);
			}
		});
		label_5Centimes.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_5Centimes.setBounds(180, 0, 80, 80);
		panel_2.add(label_5Centimes);
		
		JLabel label_10Centimes = new JLabel(new ImageIcon(piece10CentimesImage.getScaledInstance(80, 80, Image.SCALE_FAST)));
		label_10Centimes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(0.1f);
			}
		});
		label_10Centimes.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_10Centimes.setBounds(270, 0, 80, 80);
		panel_2.add(label_10Centimes);
		
		JLabel label_20Centimes = new JLabel(new ImageIcon(piece20CentimesImage.getScaledInstance(80, 80, Image.SCALE_FAST)));
		label_20Centimes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(0.2f);
			}
		});
		label_20Centimes.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_20Centimes.setBounds(358, 0, 80, 80);
		panel_2.add(label_20Centimes);
		
		JLabel label_50Centimes = new JLabel(new ImageIcon(piece50CentimesImage.getScaledInstance(80, 80, Image.SCALE_FAST)));
		label_50Centimes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(0.5f);
			}
		});
		label_50Centimes.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_50Centimes.setBounds(448, 0, 80, 80);
		panel_2.add(label_50Centimes);
		
		JLabel lblNewLabel_2 = new JLabel("1 centime");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 86, 80, 14);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblCentimes = new JLabel("2 centimes");
		lblCentimes.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentimes.setBounds(90, 86, 80, 14);
		panel_2.add(lblCentimes);
		
		JLabel lblCentimes_1 = new JLabel("5 centimes");
		lblCentimes_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentimes_1.setBounds(180, 86, 80, 14);
		panel_2.add(lblCentimes_1);
		
		JLabel lblCentimes_2 = new JLabel("10 centimes");
		lblCentimes_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentimes_2.setBounds(270, 86, 80, 14);
		panel_2.add(lblCentimes_2);
		
		JLabel lblCentimes_3 = new JLabel("20 centimes");
		lblCentimes_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentimes_3.setBounds(358, 86, 80, 14);
		panel_2.add(lblCentimes_3);
		
		JLabel lblCentimes_4 = new JLabel("50 centimes");
		lblCentimes_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentimes_4.setBounds(448, 86, 80, 14);
		panel_2.add(lblCentimes_4);
		
		JLabel label_1Euro = new JLabel(new ImageIcon(piece1EuroImage.getScaledInstance(80, 80, Image.SCALE_FAST)));
		label_1Euro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(1);
			}
		});
		label_1Euro.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1Euro.setBounds(0, 111, 80, 80);
		panel_2.add(label_1Euro);
		
		JLabel label_2Euros = new JLabel(new ImageIcon(piece2EuroImage.getScaledInstance(80, 80, Image.SCALE_FAST)));
		label_2Euros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(2);
			}
		});
		label_2Euros.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2Euros.setBounds(90, 111, 80, 80);
		panel_2.add(label_2Euros);
		
		JLabel lblEuros = new JLabel("2 Euros");
		lblEuros.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuros.setBounds(90, 197, 80, 14);
		panel_2.add(lblEuros);
		
		JLabel lblEuro = new JLabel("1 Euro");
		lblEuro.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuro.setBounds(0, 197, 80, 14);
		panel_2.add(lblEuro);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 23, 649, 68);
		panel_PaiementEnEspeces.add(panel_1);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setLayout(null);
		JLabel billet5EurosImageHolder = new JLabel(new ImageIcon(billet5EurosImage.getScaledInstance(121, 68, Image.SCALE_FAST)));
		billet5EurosImageHolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(5);
			}
		});
		billet5EurosImageHolder.setBounds(0, 0, 121, 68);
		panel_1.add(billet5EurosImageHolder);
		billet5EurosImageHolder.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel billet10EurosImageHolder = new JLabel(new ImageIcon(billet10EurosImage.getScaledInstance(121, 68, Image.SCALE_FAST)));
		billet10EurosImageHolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(10);
			}
		});
		billet10EurosImageHolder.setBounds(131, 0, 121, 68);
		panel_1.add(billet10EurosImageHolder);
		billet10EurosImageHolder.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel billet20EurosImageHolder = new JLabel(new ImageIcon(billet20EurosImage.getScaledInstance(121, 68, Image.SCALE_FAST)));
		billet20EurosImageHolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(20);
			}
		});
		billet20EurosImageHolder.setBounds(262, 0, 121, 68);
		panel_1.add(billet20EurosImageHolder);
		billet20EurosImageHolder.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel billet50EurosImageHolder = new JLabel(new ImageIcon(billet50EurosImage.getScaledInstance(121, 68, Image.SCALE_FAST)));
		billet50EurosImageHolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(50);
			}
		});
		billet50EurosImageHolder.setBounds(393, 0, 121, 68);
		panel_1.add(billet50EurosImageHolder);
		billet50EurosImageHolder.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel billet100EurosImageHolder = new JLabel(new ImageIcon(billet100EurosImage.getScaledInstance(121, 68, Image.SCALE_FAST)));
		billet100EurosImageHolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deduireMontantAPayer(100);
			}
		});
		billet100EurosImageHolder.setBounds(524, 0, 121, 68);
		panel_1.add(billet100EurosImageHolder);
		billet100EurosImageHolder.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblPices = new JLabel("Pi\u00E8ces");
		lblPices.setBounds(0, 102, 528, 14);
		panel_PaiementEnEspeces.add(lblPices);
		lblPices.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Billets");
		lblNewLabel_1.setBounds(0, 0, 528, 14);
		panel_PaiementEnEspeces.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		panel_PaiementParCarte = new JPanel();
		panel_PaiementParCarte.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		layeredPane.add(panel_PaiementParCarte, "name_134359256879800");
		panel_PaiementParCarte.setLayout(null);
		
		codePin = new JPasswordField();
		codePin.setBounds(210, 96, 86, 20);
		panel_PaiementParCarte.add(codePin);
		codePin.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Veuillez saisir votre code pin");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(150, 71, 202, 14);
		panel_PaiementParCarte.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codePinString = codePin.getText();
				
				if(codePinString.length() != 4) {
					JOptionPane.showMessageDialog(panel, "Un problème survenu. Le code pin doit avoir 4 chiffres", "Attention", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					try {
						int codePinInt = Integer.parseInt(codePinString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(panel, "Un problème survenu. Le code pin doit êttre en format numérique", "Attention", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				btnEncaisser.setEnabled(true);
			}
		});
		btnNewButton_1.setBounds(210, 127, 89, 23);
		panel_PaiementParCarte.add(btnNewButton_1);
		
		btnEncaisser = new JButton("Encaisser");
		btnEncaisser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketsActions.insererNouveauTicket();
				TicketsActions.insererTicketsProduits();
			}
		});
		btnEncaisser.setEnabled(false);
		btnEncaisser.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEncaisser.setBounds(742, 442, 270, 34);
		panel.add(btnEncaisser);
		
		JLabel lblAPayer = new JLabel("\u00C0 payer");
		lblAPayer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAPayer.setBounds(534, 0, 121, 34);
		panel.add(lblAPayer);
		
		label_MontantAPayer = new JLabel(this.montantAPayer + " EUR    ");
		label_MontantAPayer.setOpaque(true);
		label_MontantAPayer.setHorizontalAlignment(SwingConstants.TRAILING);
		label_MontantAPayer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_MontantAPayer.setBackground(SystemColor.info);
		label_MontantAPayer.setBounds(534, 30, 161, 25);
		panel.add(label_MontantAPayer);
		
		JLabel lblARendre = new JLabel("\u00C0 rendre");
		lblARendre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblARendre.setBounds(705, 0, 121, 34);
		panel.add(lblARendre);
		
		label_MontantARendre = new JLabel(this.montantARendre + " EUR    ");
		label_MontantARendre.setOpaque(true);
		label_MontantARendre.setHorizontalAlignment(SwingConstants.TRAILING);
		label_MontantARendre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_MontantARendre.setBackground(SystemColor.info);
		label_MontantARendre.setBounds(705, 30, 161, 25);
		panel.add(label_MontantARendre);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	if (e.getStateChange() == ItemEvent.SELECTED) {
		            if(String.valueOf(comboBox.getSelectedItem()).equals("Paiement en especes")) {
						switchPanels(panel_PaiementEnEspeces);
					} else if (String.valueOf(comboBox.getSelectedItem()).equals("Paiement par Carte Bancaire")) {
						switchPanels(panel_PaiementParCarte);
					}
		    }
		}});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Paiement en especes", "Paiement par Carte Bancaire"}));
		comboBox.setBounds(363, 66, 184, 22);
		panel.add(comboBox);
	}
	
	public Utilisateurs getUser()
	{
		return user;
	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public void deduireMontantAPayer (float montantADeduire) {
		if(montantAPayer > 0) {
			if (montantADeduire <= montantAPayer) {
				montantAPayer -= montantADeduire;
				label_MontantAPayer.setText(String.format("%.2f", montantAPayer));
			} else {
				montantARendre = montantADeduire - montantAPayer;
				label_MontantARendre.setText(String.format("%.2f", montantARendre));
				montantAPayer = 0;
				label_MontantAPayer.setText(Float.toString(montantAPayer));
			}
		}
		
		if (montantAPayer == 0) {
			btnEncaisser.setEnabled(true);
		}
	}
}
