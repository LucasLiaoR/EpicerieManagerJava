package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Insets;

public class CreationNouveauTicketPanel {
	private JPanel panel;
	private JTextField CreationTicketNumero;
	private JTextField CreationTicketID;
	private JTextField textField;
	private JTextField textField_1;
	
	public CreationNouveauTicketPanel ()
	{
		panel = new JPanel();
		panel.setBounds(181, 38, 851, 512);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Création d'un nouveau ticket");
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
		
		CreationTicketNumero = new JTextField();
		CreationTicketNumero.setBounds(185, 88, 152, 24);
		panel.add(CreationTicketNumero);
		CreationTicketNumero.setColumns(10);
		
		JLabel lblIdClient = new JLabel("ID Client");
		lblIdClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdClient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdClient.setBounds(28, 123, 107, 24);
		panel.add(lblIdClient);
		
		CreationTicketID = new JTextField();
		CreationTicketID.setColumns(10);
		CreationTicketID.setBounds(185, 123, 152, 24);
		panel.add(CreationTicketID);
		
		JLabel label = new JLabel("ID Client");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(28, 158, 107, 24);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(185, 158, 152, 24);
		panel.add(textField);
		
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
		
		JList list_1 = new JList();
		list_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		list_1.setBounds(10, 55, 257, 324);
		panel_1.add(list_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 216, 576, 296);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(10, 11, 249, 274);
		panel_2.add(list);
		
		JLabel lblNewLabel_3 = new JLabel("Quantit\u00E9");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(296, 84, 69, 21);
		panel_2.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setBounds(284, 114, 100, 26);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Ajouter au \r\npanier");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setMargin(new Insets(2, 5, 2, 5));
		btnNewButton_1.setBounds(416, 98, 125, 58);
		panel_2.add(btnNewButton_1);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
