package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import database.ProduitsActions;
import fr.sql.utilities.DbUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
		
		JLabel label = new JLabel("Vue d'ensemble de l'inventaire");
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
		radioBtnRupture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableProduits.setModel(DbUtils.resultSetToTableModel(ProduitsActions.getProduitsRupture()));
			}
		});
		panelDashboardInventaire.add(radioBtnRupture);
		
		JRadioButton radioBtnACommander = new JRadioButton("Filtrer sur les produits \u00E0 commander");
		radioBtnACommander.setBounds(375, 440, 234, 23);
		radioBtnACommander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableProduits.setModel(DbUtils.resultSetToTableModel(ProduitsActions.getProduitsACommander()));
			}
		});
		panelDashboardInventaire.add(radioBtnACommander);
		
		JRadioButton radioBtnAll = new JRadioButton("Tout afficher");
		radioBtnAll.setBounds(627, 440, 142, 23);
		radioBtnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableProduits.setModel(DbUtils.resultSetToTableModel(ProduitsActions.getAllProduits()));
			}
		});
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
