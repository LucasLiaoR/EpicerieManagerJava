package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import database.ProduitsActions;
import net.proteanit.sql.DbUtils;
import net.proteanit.sql.MyTableModel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CreationNouveauTicketPanel {
	private JPanel panel;
	private JTextField textField_Quantite;
	private JLabel lbNomProduitChoisi;
	private List<Integer> indexProduitsDansPanier = new ArrayList<Integer>();

	public CreationNouveauTicketPanel() {
		panel = new JPanel();
		panel.setBounds(181, 38, 851, 512);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Cr�ation d'un nouveau ticket");
		lblNewLabel_1.setBounds(325, 0, 239, 48);
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Num\u00E9ro de ticket");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 88, 107, 24);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(574, 88, 277, 424);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Panier");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 0, 277, 46);
		panel_1.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Encaisser le panier");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(0, 390, 277, 34);
		panel_1.add(btnNewButton);

		JScrollPane scrollPanePanier = new JScrollPane();
		scrollPanePanier.setBounds(10, 55, 257, 324);
		panel_1.add(scrollPanePanier);

		JTable tablePanier = new JTable();
		MyTableModel modelTablePanier = new MyTableModel(new Object[][] {},
				new String[] { "Produit", "Quantit\u00E9", "Montant" });
		tablePanier.setModel(modelTablePanier);
		tablePanier.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablePanier.getColumnModel().getColumn(0).setMinWidth(100);
		tablePanier.getColumnModel().getColumn(1).setPreferredWidth(60);
		tablePanier.getColumnModel().getColumn(2).setPreferredWidth(60);
		tablePanier.getColumnModel().getColumn(2).setMinWidth(60);
		scrollPanePanier.setViewportView(tablePanier);
		tablePanier.setBorder(new LineBorder(new Color(0, 0, 0)));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 123, 576, 389);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPaneProduits = new JScrollPane();
		scrollPaneProduits.setBounds(10, 29, 556, 274);
		panel_2.add(scrollPaneProduits);

		JTable tableProduits = new JTable();
		tableProduits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nomProduit = tableProduits
						.getValueAt(tableProduits.getSelectedRow(), tableProduits.getColumn("Nom").getModelIndex())
						.toString();
				lbNomProduitChoisi.setText(nomProduit);
			}
		});
		tableProduits.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollPaneProduits.setViewportView(tableProduits);
		tableProduits.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableProduits.setModel(DbUtils
				.resultSetToTableModel(ProduitsActions.getAllProduitBySatusResultSet("En stock", "A commander")));

		JLabel lblNewLabel_3 = new JLabel("Quantit\u00E9");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(254, 320, 69, 21);
		panel_2.add(lblNewLabel_3);

		textField_Quantite = new JTextField();
		textField_Quantite.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Quantite.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_Quantite.setBounds(254, 345, 100, 26);
		panel_2.add(textField_Quantite);
		textField_Quantite.setColumns(10);

		JButton btnAjouterAuPanier = new JButton("Ajouter au \r\npanier");
		btnAjouterAuPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomProduit = tableProduits
						.getValueAt(tableProduits.getSelectedRow(), tableProduits.getColumn("Nom").getModelIndex())
						.toString();
				String quantiteInputString = textField_Quantite.getText();
				float quantiteInputFloat = Float.parseFloat(quantiteInputString);
				String quantiteStockString = tableProduits
						.getValueAt(tableProduits.getSelectedRow(), tableProduits.getColumn("Stock").getModelIndex())
						.toString();
				float quantiteStockFloat = Float.parseFloat(quantiteStockString);
				
				if(quantiteInputFloat <= quantiteStockFloat) {
					float montantFloat = Float.parseFloat(tableProduits
							.getValueAt(tableProduits.getSelectedRow(), tableProduits.getColumn("Prix").getModelIndex())
							.toString()) * Integer.parseInt(quantiteInputString);
					String montantString = Float.toString(montantFloat);
					modelTablePanier.addRow(new Object[] { nomProduit, quantiteInputString, montantString });
					
					// Ajouter le produit choisi dans la table des produits en panier
					String indexProduitString = tableProduits
							.getValueAt(tableProduits.getSelectedRow(), tableProduits.getColumn("Id").getModelIndex())
							.toString();
					int indexProduitInt = Integer.parseInt(indexProduitString);
					indexProduitsDansPanier.add(indexProduitInt);
					
					// Modifi� la quantit� du stock dans JTable tableProduits
					quantiteStockFloat -= quantiteInputFloat;
					tableProduits.getModel().setValueAt(quantiteStockFloat, tableProduits.getSelectedRow(), tableProduits.getColumn("Stock").getModelIndex());
				} else {
					JOptionPane.showMessageDialog(panel, "La quantit� saisie est sup�rieure que le stock. Veuillez r�essayer !", "Attention", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAjouterAuPanier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAjouterAuPanier.setMargin(new Insets(2, 5, 2, 5));
		btnAjouterAuPanier.setBounds(377, 324, 150, 54);
		panel_2.add(btnAjouterAuPanier);

		JLabel lblNewLabel_4 = new JLabel("Produits");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setLabelFor(scrollPaneProduits);
		lblNewLabel_4.setBounds(10, 5, 78, 25);
		panel_2.add(lblNewLabel_4);

		JLabel lblProduitChoisi = new JLabel("Produit choisi");
		lblProduitChoisi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduitChoisi.setBounds(20, 324, 110, 21);
		panel_2.add(lblProduitChoisi);

		lbNomProduitChoisi = new JLabel("Aucun produit choisi");
		lbNomProduitChoisi.setOpaque(true);
		lbNomProduitChoisi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbNomProduitChoisi.setBackground(SystemColor.info);
		lbNomProduitChoisi.setBounds(20, 350, 212, 19);
		panel_2.add(lbNomProduitChoisi);

		JLabel numTicketCourantLabel = new JLabel("Numero ticket courant");
		numTicketCourantLabel.setOpaque(true);
		numTicketCourantLabel.setBackground(SystemColor.info);
		numTicketCourantLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		numTicketCourantLabel.setBounds(145, 90, 150, 19);
		panel.add(numTicketCourantLabel);
	}

	public JPanel getPanel() {
		return panel;
	}
}
