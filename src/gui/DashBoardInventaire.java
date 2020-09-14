package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import database.ProduitsActions;
import fr.sql.utilities.DbUtils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class DashBoardInventaire {
	
	private JPanel panelDashboardInventaire;

	public JTable tableProduits;
	
	public DashBoardInventaire()
	{
		panelDashboardInventaire = new JPanel();
		panelDashboardInventaire.setBounds(181, 38, 851, 512);
		panelDashboardInventaire.setLayout(null);
		
		JLabel label = new JLabel("Modification de la quantit\u00E9 d'un produit");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.ITALIC, 16));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(324, 0, 300, 48);
		panelDashboardInventaire.add(label);
		
		tableProduits = new JTable();
		tableProduits.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableProduits.setFillsViewportHeight(true);
		tableProduits.setModel(DbUtils.resultSetToTableModel(ProduitsActions.getAllProduits()));
		
		JScrollPane scrollPane = new JScrollPane(tableProduits);
		scrollPane.setBounds(75, 106, 694, 303);
		panelDashboardInventaire.add(scrollPane);
		
		JRadioButton radioBtnRupture = new JRadioButton("Filtrer sur les produits en rupture de stock");
		radioBtnRupture.setBounds(92, 440, 265, 23);
		panelDashboardInventaire.add(radioBtnRupture);
		
		JRadioButton radioBtnACommander = new JRadioButton("Filtrer sur les produits \u00E0 commander");
		radioBtnACommander.setBounds(375, 440, 234, 23);
		panelDashboardInventaire.add(radioBtnACommander);
		
		JRadioButton radioBtnAll = new JRadioButton("Tout afficher");
		radioBtnAll.setBounds(627, 440, 142, 23);
		radioBtnAll.setSelected(true);
		panelDashboardInventaire.add(radioBtnAll);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioBtnRupture);
		group.add(radioBtnACommander);
		group.add(radioBtnAll);
		
		
		
	}
	
	
	public JPanel getPanel() {
		return panelDashboardInventaire;
	}
}
