package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class AjoutProduitPanel {
	
	private JPanel panelAjouterProduit;
	private JTextField AjoutProduit_NomProduit;
	private JTextField AjoutProduit_IDProduit;
	private JTextField AjoutProduit_Statut;
	private JTextField AjoutProduit_PrixVente;
	private JTextField textField;
	
	public AjoutProduitPanel ()
	{
		panelAjouterProduit = new JPanel();
		panelAjouterProduit.setBounds(181, 38, 851, 512);
		panelAjouterProduit.setLayout(null);
		
		AjoutProduit_NomProduit = new JTextField();
		AjoutProduit_NomProduit.setBounds(200, 77, 591, 27);
		panelAjouterProduit.add(AjoutProduit_NomProduit);
		AjoutProduit_NomProduit.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom du produit");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 76, 163, 27);
		panelAjouterProduit.add(lblNewLabel_1);
		
		JLabel lblIdProduit = new JLabel("ID produit");
		lblIdProduit.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdProduit.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdProduit.setBounds(10, 114, 163, 27);
		panelAjouterProduit.add(lblIdProduit);
		
		AjoutProduit_IDProduit = new JTextField();
		AjoutProduit_IDProduit.setColumns(10);
		AjoutProduit_IDProduit.setBounds(200, 115, 591, 27);
		panelAjouterProduit.add(AjoutProduit_IDProduit);
		
		JLabel lblNewLabel_2 = new JLabel("Ajout d'un nouveau produit");
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(325, 0, 239, 48);
		panelAjouterProduit.add(lblNewLabel_2);
		
		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie");
		lblCatgorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatgorie.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCatgorie.setBounds(10, 152, 163, 27);
		panelAjouterProduit.add(lblCatgorie);
		
		JList AjoutProduit_ListeCategorie = new JList();
		AjoutProduit_ListeCategorie.setBounds(200, 153, 591, 26);
		panelAjouterProduit.add(AjoutProduit_ListeCategorie);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatut.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStatut.setBounds(10, 190, 163, 27);
		panelAjouterProduit.add(lblStatut);
		
		AjoutProduit_Statut = new JTextField();
		AjoutProduit_Statut.setColumns(10);
		AjoutProduit_Statut.setBounds(200, 190, 591, 27);
		panelAjouterProduit.add(AjoutProduit_Statut);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescription.setBounds(10, 228, 163, 27);
		panelAjouterProduit.add(lblDescription);
		
		JTextArea AjoutProduit_Desc = new JTextArea();
		AjoutProduit_Desc.setBounds(200, 228, 591, 124);
		panelAjouterProduit.add(AjoutProduit_Desc);
		
		JLabel lblPrixDeVente = new JLabel("Prix de vente");
		lblPrixDeVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrixDeVente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrixDeVente.setBounds(10, 406, 163, 27);
		panelAjouterProduit.add(lblPrixDeVente);
		
		AjoutProduit_PrixVente = new JTextField();
		AjoutProduit_PrixVente.setBounds(200, 404, 591, 32);
		panelAjouterProduit.add(AjoutProduit_PrixVente);
		AjoutProduit_PrixVente.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter le produit");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(553, 449, 271, 52);
		panelAjouterProduit.add(btnNewButton);
		
		JLabel lblPrixDachat = new JLabel("Prix d'achat");
		lblPrixDachat.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrixDachat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrixDachat.setBounds(10, 365, 163, 27);
		panelAjouterProduit.add(lblPrixDachat);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(200, 363, 591, 32);
		panelAjouterProduit.add(textField);
	}
	
	public JPanel getPanel() {
		return panelAjouterProduit;
	}
}
