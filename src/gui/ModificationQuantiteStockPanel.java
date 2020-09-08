package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ModificationQuantiteStockPanel {
	private JPanel panel;
	
	public ModificationQuantiteStockPanel ()
	{
		panel = new JPanel();
		panel.setBounds(181, 38, 851, 512);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Modification de la quantité d'un produit");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(325, 0, 300, 48);
		panel.add(lblNewLabel_1);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
