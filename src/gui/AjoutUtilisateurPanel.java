package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import database.UtilisateursActions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class AjoutUtilisateurPanel {
	private JPanel panel;
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField textFieldTelephone;
	private JTextField textField_Email;
	private JTextField textField_Adresse;
	private JPasswordField passwordField;
	private JTextField caisseTextField;
	private JCheckBox isManagerCheckBox;
	
	public AjoutUtilisateurPanel ()
	{
		panel = new JPanel();
		panel.setBounds(181, 38, 851, 512);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ajout d'un nouvel utilisateur");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(325, 0, 239, 48);
		panel.add(lblNewLabel_1);
		
		JLabel nomLabel = new JLabel("Nom");
		nomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		nomLabel.setBounds(40, 64, 62, 16);
		panel.add(nomLabel);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(173, 59, 588, 29);
		panel.add(nomTextField);
		nomTextField.setColumns(10);
		
		JLabel prenomLabel = new JLabel("Pr\u00E9nom");
		prenomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		prenomLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		prenomLabel.setBounds(40, 116, 62, 16);
		panel.add(prenomLabel);
		
		prenomTextField = new JTextField();
		prenomTextField.setColumns(10);
		prenomTextField.setBounds(173, 111, 588, 29);
		panel.add(prenomTextField);
		
		JLabel telLabel = new JLabel("T\u00E9l\u00E9phone");
		telLabel.setHorizontalAlignment(SwingConstants.CENTER);
		telLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		telLabel.setBounds(28, 166, 90, 16);
		panel.add(telLabel);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setColumns(10);
		textFieldTelephone.setBounds(173, 161, 588, 29);
		panel.add(textFieldTelephone);
		
		JLabel lblAdresseMail = new JLabel("Adresse mail");
		lblAdresseMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresseMail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdresseMail.setBounds(28, 217, 97, 16);
		panel.add(lblAdresseMail);
		
		textField_Email = new JTextField();
		textField_Email.setColumns(10);
		textField_Email.setBounds(173, 212, 588, 29);
		panel.add(textField_Email);
		
		JLabel lblAdresse = new JLabel("Adresse postale");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdresse.setBounds(30, 269, 106, 16);
		panel.add(lblAdresse);
		
		textField_Adresse = new JTextField();
		textField_Adresse.setColumns(10);
		textField_Adresse.setBounds(173, 264, 588, 29);
		panel.add(textField_Adresse);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMotDePasse.setBounds(28, 318, 106, 16);
		panel.add(lblMotDePasse);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(173, 313, 588, 29);
		panel.add(passwordField);
		
		JLabel lblCaisse = new JLabel("Caisse");
		lblCaisse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaisse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCaisse.setBounds(28, 372, 106, 16);
		panel.add(lblCaisse);
		
		JLabel lblUtilisateurManager = new JLabel("Utilisateur Manager");
		lblUtilisateurManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblUtilisateurManager.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUtilisateurManager.setBounds(28, 412, 135, 16);
		panel.add(lblUtilisateurManager);
		
		isManagerCheckBox = new JCheckBox("");
		isManagerCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		isManagerCheckBox.setBounds(173, 410, 33, 23);
		panel.add(isManagerCheckBox);
		
		caisseTextField = new JTextField();
		caisseTextField.setColumns(10);
		caisseTextField.setBounds(173, 367, 588, 29);
		panel.add(caisseTextField);
		
		JButton ajouterClientBtn = new JButton("Ajouter l'utilisateur");
		ajouterClientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (Component component : panel.getComponents()) {
					if (component.getClass().equals(JTextField.class)) {
						JTextField jtf = (JTextField)component;
						if(jtf.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Veuillez saisir tous les champs !", "Attention", JOptionPane.ERROR_MESSAGE);
							return;
						}
				    }
				}
				
				int isManager = 0;
				if(isManagerCheckBox.isSelected()) {
					isManager = 1;
				}
				if(UtilisateursActions.ajouterUtilisateur(nomTextField.getText(), prenomTextField.getText(), textFieldTelephone.getText(), textField_Email.getText(), textField_Adresse.getText(), passwordField.getText(), caisseTextField.getText(), isManager)) {
					JOptionPane.showMessageDialog(panel, "Nouvel utlisateur ajouté avec succès !", "Succès", JOptionPane.PLAIN_MESSAGE);
					nomTextField.setText("");
					prenomTextField.setText("");
					textFieldTelephone.setText("");
					textField_Email.setText("");
					textField_Adresse.setText("");
					passwordField.setText("");
					caisseTextField.setText("");
					isManagerCheckBox.setSelected(false);
				} else {
					JOptionPane.showMessageDialog(panel, "Un problème survenu. Veuillez vérifier votre saisie", "Attention", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		ajouterClientBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		ajouterClientBtn.setBounds(595, 443, 246, 58);
		panel.add(ajouterClientBtn);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
