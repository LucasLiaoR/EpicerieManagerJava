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
import javax.swing.UIManager;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import database.UtilisateursActions;
import database.Categories;
import database.CategoriesActions;
import database.ProduitsActions;

public class AjoutProduitPanel {
	
	private JPanel panelAjouterProduit;
	private JTextField AjoutProduit_NomProduit;
	private JTextField AjoutProduit_Statut;
	private JTextField AjoutProduit_PrixVente;
	private JTextField AjoutProduitUniteProd;
	private JTextField AjoutProduitQuantiteMinimum;
	
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
		
		JLabel lblNewLabel_2 = new JLabel("Ajout d'un nouveau produit");
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(325, 0, 239, 48);
		panelAjouterProduit.add(lblNewLabel_2);
		
		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie");
		lblCatgorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatgorie.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCatgorie.setBounds(10, 411, 163, 27);
		panelAjouterProduit.add(lblCatgorie);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatut.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStatut.setBounds(10, 367, 163, 27);
		panelAjouterProduit.add(lblStatut);
		
		AjoutProduit_Statut = new JTextField();
		AjoutProduit_Statut.setColumns(10);
		AjoutProduit_Statut.setBounds(200, 368, 591, 27);
		panelAjouterProduit.add(AjoutProduit_Statut);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescription.setBounds(10, 142, 163, 27);
		panelAjouterProduit.add(lblDescription);
		
		JTextArea AjoutProduit_Desc = new JTextArea();
		AjoutProduit_Desc.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		AjoutProduit_Desc.setBounds(200, 122, 591, 67);
		panelAjouterProduit.add(AjoutProduit_Desc);
		
		JLabel lblPrixDeVente = new JLabel("Prix de vente");
		lblPrixDeVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrixDeVente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrixDeVente.setBounds(10, 208, 163, 27);
		panelAjouterProduit.add(lblPrixDeVente);
		
		AjoutProduit_PrixVente = new JTextField();
		AjoutProduit_PrixVente.setBounds(200, 200, 591, 32);
		panelAjouterProduit.add(AjoutProduit_PrixVente);
		AjoutProduit_PrixVente.setColumns(10);
		
		JComboBox AjoutProduitCate = new JComboBox();
		AjoutProduitCate.setBounds(200, 412, 591, 27);
		panelAjouterProduit.add(AjoutProduitCate);
		
		List<Categories> listeCate = new ArrayList<Categories>();
		
		listeCate = CategoriesActions.getCategoriesDB();
		
		for (Categories c : listeCate)
		{
			AjoutProduitCate.addItem(c.getLibelle());
		}
		
		JButton btnNewButton = new JButton("Ajouter le produit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int idCate = 1;
				
				List<Categories> listeCategories = new ArrayList<Categories>();
				
				listeCategories = CategoriesActions.getCategoriesDB();
				
				for (int i = 1; i<listeCategories.size(); i++)
				{
					if (listeCategories.get(i).getLibelle().equals(AjoutProduitCate.getSelectedItem()))
					{
						idCate = i+1;
					}
				}
				
				ProduitsActions.ajouterProduit(0, AjoutProduit_NomProduit.getText(), AjoutProduit_Desc.getText(), Integer.parseInt(AjoutProduit_PrixVente.getText()), Integer.parseInt(AjoutProduitQuantiteMinimum.getText()), 0, AjoutProduitUniteProd.getText(), AjoutProduit_Statut
.getText(), idCate);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(553, 449, 271, 52);
		panelAjouterProduit.add(btnNewButton);
		
		JLabel ProdUnite = new JLabel("Unite de compte");
		ProdUnite.setHorizontalAlignment(SwingConstants.CENTER);
		ProdUnite.setFont(new Font("Tahoma", Font.BOLD, 13));
		ProdUnite.setBounds(10, 310, 163, 27);
		panelAjouterProduit.add(ProdUnite);
		
		AjoutProduitUniteProd = new JTextField();
		AjoutProduitUniteProd.setColumns(10);
		AjoutProduitUniteProd.setBounds(200, 308, 591, 32);
		panelAjouterProduit.add(AjoutProduitUniteProd);
		
		
		
		JLabel lblQuantitMinimum = new JLabel("Quantit\u00E9 minimum");
		lblQuantitMinimum.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantitMinimum.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantitMinimum.setBounds(10, 258, 163, 27);
		panelAjouterProduit.add(lblQuantitMinimum);
		
		AjoutProduitQuantiteMinimum = new JTextField();
		AjoutProduitQuantiteMinimum.setColumns(10);
		AjoutProduitQuantiteMinimum.setBounds(200, 256, 591, 32);
		panelAjouterProduit.add(AjoutProduitQuantiteMinimum);
	}
	
	public JPanel getPanel() {
		return panelAjouterProduit;
	}
}
