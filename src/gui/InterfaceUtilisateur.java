package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class InterfaceUtilisateur implements Runnable {

	private JFrame frame;
	private final JPanel TopPanel = new JPanel();
	private JTextField dateHeure;
	private JTextField headText;
	private JPanel LeftPanel;
	private JPanel MenuPrincipal;
	private JLabel lblNewLabel;

	public void run() {
		try {
			InterfaceUtilisateur window = new InterfaceUtilisateur();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public InterfaceUtilisateur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1048, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		TopPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		TopPanel.setBounds(0, 0, 1032, 39);
		frame.getContentPane().add(TopPanel);
		TopPanel.setLayout(null);

		dateHeure = new JTextField();
		dateHeure.setEditable(false);
		dateHeure.setBounds(10, 11, 164, 18);
		TopPanel.add(dateHeure);
		dateHeure.setColumns(10);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date date = new Date();

		dateHeure.setText(date.toString());

		headText = new JTextField();
		headText.setEditable(false);
		headText.setFont(new Font("Tahoma", Font.BOLD, 11));
		headText.setText("Projet Java - Outil Caisse/Inventaire d'\u00E9picerie");
		headText.setColumns(10);
		headText.setBounds(413, 11, 275, 18);
		TopPanel.add(headText);

		LeftPanel = new JPanel();
		LeftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		LeftPanel.setBounds(0, 38, 182, 512);
		frame.getContentPane().add(LeftPanel);
		LeftPanel.setLayout(null);

		MenuPrincipal = new JPanel();
		MenuPrincipal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		MenuPrincipal.setBounds(10, 11, 162, 154);
		LeftPanel.add(MenuPrincipal);
		MenuPrincipal.setLayout(null);

		lblNewLabel = new JLabel("Menu Principal");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(0, 0, 162, 30);
		MenuPrincipal.add(lblNewLabel);
	}
}
