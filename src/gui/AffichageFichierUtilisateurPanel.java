package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.ProduitsActions;
import database.Utilisateurs;
import database.UtilisateursActions;
import fr.sql.utilities.DbUtils;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class AffichageFichierUtilisateurPanel {
	private JPanel panel;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTable table;
	

	public static DefaultListModel listRecherche = new DefaultListModel();
	public static List<Utilisateurs> listeUtilisateursDB = new ArrayList<Utilisateurs>();
	private static String valeurSelected;
	public static int idRecherche = 1;
	
	/**
	 * @wbp.nonvisual location=-43,159
	 */
	private final JScrollPane scrollPane_2 = new JScrollPane();
	
	public AffichageFichierUtilisateurPanel ()
	{
		panel = new JPanel();
		panel.setBounds(181, 38, 851, 512);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Changer d'utilisateur");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(325, 0, 239, 48);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Nom utilisateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 59, 163, 30);
		panel.add(lblNewLabel);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(183, 59, 163, 33);
		panel.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblPrnomRechercher = new JLabel("Prenom utilisateur");
		lblPrnomRechercher.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrnomRechercher.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrnomRechercher.setBounds(10, 110, 163, 30);
		panel.add(lblPrnomRechercher);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(183, 110, 163, 33);
		panel.add(textFieldPrenom);
		
		JList list = new JList(listRecherche);
		list.setBounds(28, 219, 475, 267);
		
		JButton btnNewButton = new JButton("Rechercher un utilisateur");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				listRecherche.clear();
				
				if (!textFieldNom.getText().toString().equals("") && !textFieldPrenom.getText().toString().equals(""))
				{
					listeUtilisateursDB = UtilisateursActions.getUtilisateurs();
					
					String inputNom = textFieldNom.getText().toString();
					String inputPrenom = textFieldPrenom.getText().toString();
					
					for (Utilisateurs u : listeUtilisateursDB)
					{
						if (u.getNom().equals(inputNom) || u.getPrenom().equals(inputPrenom))
						{
							listRecherche.addElement(Integer.valueOf(u.getId()).toString() + " - " + u.getPrenom().toString() + " - " + u.getNom().toString());
						}
					}
				}
				
				list.updateUI();
				
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(99, 170, 183, 30);
		panel.add(btnNewButton);
		
		
		
		list.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event)
					{
						if (list.getSelectedValue() != null)
						{
							valeurSelected = list.getSelectedValue().toString();
							
							
							String idString = "";
							int i = 0;
						
							
							while (valeurSelected.charAt(i) != ' ')
							{
								idString = idString + valeurSelected.charAt(i);
								i++;
							}
							
							idRecherche = Integer.parseInt(idString);
							
							table.setModel(DbUtils.resultSetToTableModel(UtilisateursActions.getUtilisateurSingle(idRecherche)));
						}
						else
						{
							valeurSelected = "";
						}
					}
				}
		);
		
		JLabel lblNewLabel_2 = new JLabel("R\u00E9sultats");
		lblNewLabel_2.setBounds(28, 218, 70, 14);
		panel.add(lblNewLabel_2);
		
		table = new JTable();
		table.setBounds(527, 80, 314, 347);
		
		JLabel lblNewLabel_3 = new JLabel("Fiche utilisateur");
		lblNewLabel_3.setBounds(560, 59, 155, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Selectionner l'utilisateur");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (list.getSelectedValue() != null)
				{
					String pass = JOptionPane.showInputDialog("Saisir le mot de passe").toString();
					
					Utilisateurs user = UtilisateursActions.authentificationUtilisateur(pass, idRecherche);
					
					if (user != null)
					{
						InterfaceUtilisateur.setUser(user);
						InterfaceUtilisateur.setLabelUser();
						JOptionPane.showMessageDialog(null, "Changement d'utilisateur avec succès !", "Changement de user !", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Echec de l'authentification !", "Erreur !", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Aucun utilisateur sélectionné !", "Erreur !", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(578, 453, 263, 33);
		panel.add(btnNewButton_1);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(400, 80, 441, 347);
		panel.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(28, 243, 314, 196);
		panel.add(scrollPane);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
