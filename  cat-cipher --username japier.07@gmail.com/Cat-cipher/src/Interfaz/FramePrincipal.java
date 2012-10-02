package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.Crypto;

public class FramePrincipal implements ActionListener{
	private JFrame frame;
	private JLabel banner;
	private JTextField llaveText;
	private JTextArea textoEncript, textoDecript;
	private JButton invertir;
	private Crypto cp;
	public FramePrincipal(Crypto cp) {
		this.cp=cp;
		frame = new JFrame("Cat-Cipher 1.0");
		frame.setSize(new Dimension(400, 350));
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/icon.png"));

		banner = new JLabel();
		banner.setPreferredSize(new Dimension(395, 50));
		banner.setBorder(BorderFactory.createLineBorder(Color.black));
		banner.setIcon(new ImageIcon("imagenes/banner.png"));

		JLabel panelLlave = new JLabel();
		panelLlave.setPreferredSize(new Dimension(390, 35));
		panelLlave.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel llave = new JLabel("Llave:");
		llaveText = new JTextField();
		llaveText.setPreferredSize(new Dimension(150, 30));
		llaveText.setDocument(new Permitidos());
		JButton convertir = new JButton("Resolver");
		convertir.addActionListener(this);
		convertir.setActionCommand("convert");

		panelLlave.add(llave);
		panelLlave.add(llaveText);
		panelLlave.add(convertir);

		JLabel panelNames = new JLabel();
		panelNames.setPreferredSize(new Dimension(390,52));
		panelNames.setLayout(new FlowLayout());

		JLabel enconde = new JLabel("Texto plano:");
		enconde.setPreferredSize(new Dimension(100, 30));
		invertir = new JButton();
		invertir.setActionCommand("inver");

		invertir.addActionListener(this);
		invertir.setBorderPainted(false);
		invertir.setContentAreaFilled(false);
		pintarBordes();

		JLabel deconde = new JLabel("Texto encriptado:");
		deconde.setPreferredSize(new Dimension(100, 30));
		panelNames.add(enconde);
		panelNames.add(invertir);
		panelNames.add(deconde);

		JLabel panelTextos = new JLabel();
		panelTextos.setPreferredSize(new Dimension(390, 170));
		panelTextos.setLayout(new FlowLayout(FlowLayout.CENTER));

		textoDecript = new JTextArea(8, 10);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(textoDecript);
		textoDecript.setLineWrap(true);
		textoDecript.setBorder(BorderFactory.createLineBorder(Color.black));
		textoDecript.setDocument(new Permitidos());


		textoEncript = new JTextArea(8, 10);
		JScrollPane scroll1 = new JScrollPane();
		scroll1.setViewportView(textoEncript);
		textoEncript.setLineWrap(true);
		textoEncript.setBorder(BorderFactory.createLineBorder(Color.black));
		textoEncript.setDocument(new Permitidos());

		scroll.setPreferredSize(new Dimension(180, 140));
		scroll1.setPreferredSize(new Dimension(180, 140));

		panelTextos.add(scroll1);
		panelTextos.add(scroll);

		frame.getContentPane().add(banner);
		frame.getContentPane().add(panelLlave);
		frame.getContentPane().add(panelNames);
		frame.getContentPane().add(panelTextos);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	private void pintarBordes() {
		if(Crypto.sentido)
		{
			invertir.setIcon(new ImageIcon("imagenes/derecha.png"));
		}
		else
		{
			invertir.setIcon(new ImageIcon("imagenes/izquierda.png"));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand();
		if(action.equals("inver"))
		{
			Crypto.sentido=!Crypto.sentido;
			pintarBordes();
		}
		else if(action.equals("convert"))
		{
			if(Crypto.sentido)
			{
				textoDecript.setText(cp.encodecode(2, llaveText.getText(), textoEncript.getText()));
			}
			else 
			{
				textoEncript.setText(cp.encodecode(1, llaveText.getText(), textoDecript.getText()));
			}
		}
	}
}
