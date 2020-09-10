package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;

public class AffichageFichierUtilisateurPanel {
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	
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
		
		textField = new JTextField();
		textField.setBounds(183, 59, 320, 33);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPrnomRechercher = new JLabel("ID utilisateur");
		lblPrnomRechercher.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrnomRechercher.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrnomRechercher.setBounds(10, 110, 163, 30);
		panel.add(lblPrnomRechercher);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(183, 110, 320, 33);
		panel.add(textField_1);
		
		JButton btnNewButton = new JButton("Rechercher un utilisateur");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(320, 164, 183, 30);
		panel.add(btnNewButton);
		
		JList list = new JList();
		list.setBounds(28, 219, 475, 267);
		panel.add(list);
		
		JLabel lblNewLabel_2 = new JLabel("R\u00E9sultats");
		lblNewLabel_2.setBounds(28, 198, 46, 14);
		panel.add(lblNewLabel_2);
		
		table = new JTable();
		table.setBounds(527, 80, 314, 347);
		panel.add(table);
		
		JLabel lblNewLabel_3 = new JLabel("Fiche utilisateur");
		lblNewLabel_3.setBounds(527, 59, 89, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Selectionner l'utilisateur");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(578, 453, 263, 33);
		panel.add(btnNewButton_1);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
