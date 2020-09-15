package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import database.UtilisateursActions;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifierInfosUtilisateurPanel {
	private JPanel panelModifierInfosUtilisateur;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtTelephone;
	private JTextField txtEmail;
	private JTextField txtAdresse;
	private JPasswordField passwordField;

	
	public ModifierInfosUtilisateurPanel()
	{
		panelModifierInfosUtilisateur = new JPanel();
		panelModifierInfosUtilisateur.setBounds(181, 38, 851, 512);
		panelModifierInfosUtilisateur.setLayout(null);
		
		JLabel lblModifierLaFiche = new JLabel("Modifier la fiche de l'utilisateur");
		lblModifierLaFiche.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifierLaFiche.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblModifierLaFiche.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblModifierLaFiche.setBounds(324, 0, 300, 48);
		panelModifierInfosUtilisateur.add(lblModifierLaFiche);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(152, 118, 149, 22);
		panelModifierInfosUtilisateur.add(lblNewLabel);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrnom.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrnom.setBounds(152, 162, 149, 22);
		panelModifierInfosUtilisateur.add(lblPrnom);
		
		JLabel lblTlphone = new JLabel("T\u00E9l\u00E9phone");
		lblTlphone.setHorizontalAlignment(SwingConstants.CENTER);
		lblTlphone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTlphone.setBounds(152, 206, 149, 22);
		panelModifierInfosUtilisateur.add(lblTlphone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(152, 250, 149, 22);
		panelModifierInfosUtilisateur.add(lblEmail);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdresse.setBounds(152, 293, 149, 22);
		panelModifierInfosUtilisateur.add(lblAdresse);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMotDePasse.setBounds(152, 336, 149, 22);
		panelModifierInfosUtilisateur.add(lblMotDePasse);
		
		txtNom = new JTextField(InterfaceUtilisateur.getUser().getNom());
		txtNom.setBounds(346, 113, 391, 34);
		panelModifierInfosUtilisateur.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField(InterfaceUtilisateur.getUser().getPrenom());
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(346, 157, 391, 34);
		panelModifierInfosUtilisateur.add(txtPrenom);
		
		txtTelephone = new JTextField(InterfaceUtilisateur.getUser().getTelephone());
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(346, 201, 391, 34);
		panelModifierInfosUtilisateur.add(txtTelephone);
		
		txtEmail = new JTextField(InterfaceUtilisateur.getUser().getMail());
		txtEmail.setColumns(10);
		txtEmail.setBounds(346, 245, 391, 34);
		panelModifierInfosUtilisateur.add(txtEmail);
		
		txtAdresse = new JTextField(InterfaceUtilisateur.getUser().getAdresse());
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(346, 288, 391, 34);
		panelModifierInfosUtilisateur.add(txtAdresse);
		
		JButton btnNewButton = new JButton("Confirmer les modifications");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNom.getText().toString().isEmpty() || !txtPrenom.getText().toString().isEmpty() || !txtTelephone.getText().toString().isEmpty() || !txtEmail.getText().toString().isEmpty() || !txtAdresse.getText().toString().isEmpty() || !passwordField.getPassword().toString().isEmpty())
				{
					// update des données dans l'attribut user de la classe InterfaceUtilisateur
					InterfaceUtilisateur.getUser().setNom(txtNom.getText().toString());
					InterfaceUtilisateur.getUser().setPrenom(txtPrenom.getText().toString());
					InterfaceUtilisateur.getUser().setTelephone(txtTelephone.getText().toString());
					InterfaceUtilisateur.getUser().setMail(txtEmail.getText().toString());
					InterfaceUtilisateur.getUser().setAdresse(txtAdresse.getText().toString());
					InterfaceUtilisateur.getUser().setMdp(String.copyValueOf(passwordField.getPassword()));
					InterfaceUtilisateur.setLabelUser();
					
					
					// update les données dans la BDD
					UtilisateursActions.modifierInfosUtilisateur(InterfaceUtilisateur.getUser());
					
					JOptionPane.showMessageDialog(null, "Informations modifiées avec succès !", "Changement des données d'utilisateur !", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton.setBounds(308, 415, 197, 48);
		panelModifierInfosUtilisateur.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(346, 333, 391, 34);
		panelModifierInfosUtilisateur.add(passwordField);
	}
	
	public JPanel getPanel()
	{
		return panelModifierInfosUtilisateur;
	}
}
