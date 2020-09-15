package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Produits;
import database.ProduitsActions;
import database.TicketsActions;
import database.UtilisateursActions;
import fr.sql.utilities.DbUtils;

import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Insets;

public class ModificationQuantiteStockPanel {
	private JPanel panel;
	private JTextField ModifQuantiteProduit_NomID;
	private JTextField ModifQuantiteProduit_Quantite;
	private JTextField ModifierQuantiteProduitPrix;
	private JTable jTableQuantitePrix;
	
	
	private static DefaultListModel listRecherche = new DefaultListModel();
	private static String valeurSelected;
	
	public ModificationQuantiteStockPanel ()
	{
	
		panel = new JPanel();
		panel.setBounds(181, 38, 851, 512);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Modification de la quantité d'un produit");
		lblNewLabel_1.setBounds(324, 0, 300, 48);
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Nom du produit");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(23, 99, 149, 26);
		panel.add(lblNewLabel);
		
		JLabel lblQuantit = new JLabel("Changement quantit\u00E9 (+/-)");
		lblQuantit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantit.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantit.setBounds(22, 388, 181, 26);
		panel.add(lblQuantit);
		
		ModifQuantiteProduit_NomID = new JTextField();
		ModifQuantiteProduit_NomID.setBounds(229, 101, 235, 24);
		panel.add(ModifQuantiteProduit_NomID);
		ModifQuantiteProduit_NomID.setColumns(10);
		
		ModifQuantiteProduit_Quantite = new JTextField();
		ModifQuantiteProduit_Quantite.setColumns(10);
		ModifQuantiteProduit_Quantite.setBounds(232, 390, 288, 24);
		panel.add(ModifQuantiteProduit_Quantite);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrix.setBounds(23, 425, 149, 26);
		panel.add(lblPrix);
		
		ModifierQuantiteProduitPrix = new JTextField();
		ModifierQuantiteProduitPrix.setColumns(10);
		ModifierQuantiteProduitPrix.setBounds(232, 427, 288, 24);
		panel.add(ModifierQuantiteProduitPrix);
		
		JList listeProduitRecherche = new JList(listRecherche);
		listeProduitRecherche.setBorder(new LineBorder(new Color(0, 0, 0)));
		listeProduitRecherche.setBounds(23, 219, 300, 122);
		listeProduitRecherche.setVisibleRowCount(5);
		
		// Action lors du changement de valeur selectionné dans la liste
		listeProduitRecherche.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event)
					{
						if (listeProduitRecherche.getSelectedValue() != null)
						{
							valeurSelected = listeProduitRecherche.getSelectedValue().toString();
							
							jTableQuantitePrix.setModel(DbUtils.resultSetToTableModel(ProduitsActions.getProduitSingleByName(valeurSelected)));
						}
						else
						{
							valeurSelected = "";
						}
					}
				}
		);
	
		
		JButton btnNewButton = new JButton("Enregistrer les modifications");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Il faut sélectionner un produit dans jTableQuantitePrix
				if(jTableQuantitePrix.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(panel, "Aucun produit choisi. Il faut cliquer sur un produit dans la table à droit pour faire le choix !", "Attention", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// Vérifié si c'est une valeur numérique
				float quantiteInputFloat;
				float prix;
				try {
					quantiteInputFloat = Float.parseFloat(ModifQuantiteProduit_Quantite.getText());
					prix = Float.parseFloat(ModifierQuantiteProduitPrix.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Il faut saisir les valeurs numériques pour la quantité et le prix !", "Attention !",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// Le prix doit etre positive
				if(prix <= 0) {
					JOptionPane.showMessageDialog(null, "Il faut que le prix soit positive !", "Attention !",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// La quantite en stock ne peut pas être negative
				String quantiteStockString = jTableQuantitePrix
						.getValueAt(jTableQuantitePrix.getSelectedRow(), jTableQuantitePrix.getColumn("Stock").getModelIndex())
						.toString();
				float quantiteStockFloat = Float.parseFloat(quantiteStockString);
				if(quantiteInputFloat + quantiteStockFloat < 0) {
					JOptionPane.showMessageDialog(null, "La quantié en stock ne peut pas être négative. Il faut que la quantité à déduire soit inférieure à la quantité en stock.", "Attention !",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (listeProduitRecherche.getSelectedValue() != null && !ModifQuantiteProduit_Quantite.getText().equals("") && !ModifierQuantiteProduitPrix.getText().equals(""))
				{
					valeurSelected = listeProduitRecherche.getSelectedValue().toString();
					
					Produits p = null;
					
					// parcourir la liste des produits de la BDD pour retrouver celui qui a été selectionné par l'utilisateur et qu'on doit modifier
					List<Produits> listeProduitDB = new ArrayList<Produits>();
					
					listeProduitDB = ProduitsActions.getProduitsDB();
					
					for (Produits prod : listeProduitDB)
					{
						if (prod.getProd_nom().equals(valeurSelected))
						{
							p = prod;
						}
					}
					
					ProduitsActions.modifierStockEtPrixProduit(Float.valueOf(ModifQuantiteProduit_Quantite.getText().toString()), Float.valueOf(ModifierQuantiteProduitPrix.getText().toString()), p);
					
					valeurSelected = listeProduitRecherche.getSelectedValue().toString();
					
					jTableQuantitePrix.setModel(DbUtils.resultSetToTableModel(ProduitsActions.getProduitSingleByName(valeurSelected)));
					
					JOptionPane.showMessageDialog(null, "Les modifications ont bien été prises en compte !", "Modification Stock !", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Il faut remplir tous les champs !", "Erreur !", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(592, 426, 235, 56);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Rechercher produit");
		btnNewButton_1.setBounds(85, 153, 145, 41);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String champRecherche = ModifQuantiteProduit_NomID.getText();
				Pattern pattern = Pattern.compile(champRecherche, Pattern.CASE_INSENSITIVE);
				Matcher matcher;
				boolean matchFound;
				
				listRecherche.clear();
				
				
				List<Produits> arrayListProduits = new ArrayList<Produits>();
				
				arrayListProduits = ProduitsActions.getProduitsDB();
				
				for (Produits p : arrayListProduits)
				{
					matcher = pattern.matcher(p.getProd_nom().toString());
					matchFound = matcher.find();
					
					if (matchFound)
					{
						listRecherche.addElement(p.getProd_nom().toString());
					}
				}
				
				
				listeProduitRecherche.updateUI();
			}
		});
		panel.add(btnNewButton_1);
		
		JButton button = new JButton("Reinitialiser recherche");
		button.setMargin(new Insets(2, 5, 2, 5));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valeurSelected = "";
				ModifQuantiteProduit_NomID.setText(null);
				listRecherche.clear();
				jTableQuantitePrix.setModel(DbUtils.resultSetToTableModel(ProduitsActions.getProduitSingleByName("")));
				listeProduitRecherche.updateUI();
			}
		});
		button.setBounds(304, 153, 145, 41);
		panel.add(button);
		
		jTableQuantitePrix = new JTable();
		jTableQuantitePrix.setBorder(new LineBorder(new Color(0, 0, 0)));
		jTableQuantitePrix.setBounds(350, 220, 477, 121);
		jTableQuantitePrix.setFillsViewportHeight(true);
		
		jTableQuantitePrix.setModel(DbUtils.resultSetToTableModel(ProduitsActions.getProduitSingleByName("")));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(333, 220, 508, 121);
		scrollPane_1.setViewportView(jTableQuantitePrix);
		panel.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane(listeProduitRecherche);
		scrollPane.setBounds(26, 219, 297, 122);
		panel.add(scrollPane);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
