package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FramePrincipal {
	private JFrame frame;
	private JLabel banner;
	private JTextField llaveText;
	public FramePrincipal() {
		frame = new JFrame();
		frame.setSize(new Dimension(400, 300));
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		banner = new JLabel();
		banner.setPreferredSize(new Dimension(395, 50));
		banner.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel panelLlave = new JLabel();
		panelLlave.setPreferredSize(new Dimension(390, 50));
		panelLlave.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel llave = new JLabel("Llave:");
		llaveText = new JTextField();
		llaveText.setPreferredSize(new Dimension(120, 30));
		panelLlave.add(llave);
		panelLlave.add(llaveText);
		
		
		frame.getContentPane().add(banner);
		frame.getContentPane().add(panelLlave);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
