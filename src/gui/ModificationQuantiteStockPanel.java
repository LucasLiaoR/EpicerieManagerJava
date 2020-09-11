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

import database.Produits;
import database.UtilisateursActions;

import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;

public class ModificationQuantiteStockPanel {
	private JPanel panel;
	private JTextField ModifQuantiteProduit_NomID;
	private JTextField ModifQuantiteProduit_Quantite;
	private JTextField ModifierQuantiteProduitPrix;
	private JTable jTableQuantitePrix;
	
	
	private static DefaultListModel listRecherche = new DefaultListModel();
	
	
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
		listeProduitRecherche.setBounds(23, 219, 497, 122);
		listeProduitRecherche.setVisibleRowCount(5);
		panel.add(listeProduitRecherche);
		
		JButton btnNewButton = new JButton("Enregistrer les modifications");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				
				arrayListProduits = UtilisateursActions.getProduitsDB();
				
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
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModifQuantiteProduit_NomID.setText(null);
				listRecherche.clear();
				listeProduitRecherche.updateUI();
			}
		});
		button.setBounds(304, 153, 145, 41);
		panel.add(button);
		
		jTableQuantitePrix = new JTable();
		jTableQuantitePrix.setBorder(new LineBorder(new Color(0, 0, 0)));
		jTableQuantitePrix.setBounds(596, 220, 231, 121);
		panel.add(jTableQuantitePrix);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
