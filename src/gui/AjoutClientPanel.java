package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AjoutClientPanel {
	private JPanel panel;
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public AjoutClientPanel ()
	{
		panel = new JPanel();
		panel.setBounds(181, 38, 851, 512);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ajout d'un nouveau client");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(325, 0, 239, 48);
		panel.add(lblNewLabel_1);
		
		JLabel nomLabel = new JLabel("Nom");
		nomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		nomLabel.setBounds(40, 84, 62, 16);
		panel.add(nomLabel);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(173, 79, 588, 29);
		panel.add(nomTextField);
		nomTextField.setColumns(10);
		
		JLabel prenomLabel = new JLabel("Pr\u00E9nom");
		prenomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		prenomLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		prenomLabel.setBounds(40, 136, 62, 16);
		panel.add(prenomLabel);
		
		prenomTextField = new JTextField();
		prenomTextField.setColumns(10);
		prenomTextField.setBounds(173, 131, 588, 29);
		panel.add(prenomTextField);
		
		JLabel telLabel = new JLabel("T\u00E9l\u00E9phone");
		telLabel.setHorizontalAlignment(SwingConstants.CENTER);
		telLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		telLabel.setBounds(28, 186, 90, 16);
		panel.add(telLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(173, 181, 588, 29);
		panel.add(textField);
		
		JLabel lblAdresseMail = new JLabel("Adresse mail");
		lblAdresseMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresseMail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdresseMail.setBounds(28, 237, 97, 16);
		panel.add(lblAdresseMail);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(173, 232, 588, 29);
		panel.add(textField_1);
		
		JLabel lblAdresse = new JLabel("Adresse postale");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdresse.setBounds(30, 289, 106, 16);
		panel.add(lblAdresse);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(173, 284, 588, 29);
		panel.add(textField_2);
		
		JButton ajouterClientBtn = new JButton("Ajouter le client");
		ajouterClientBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		ajouterClientBtn.setBounds(515, 371, 246, 58);
		panel.add(ajouterClientBtn);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
