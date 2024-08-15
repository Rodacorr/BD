package CapaGrafica;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaChat extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	
	private void initialize() {
		frame = new JFrame(); 
		Container panel = frame.getContentPane(); 
		panel.setBackground(new Color(240,248,255));
		frame.setTitle("Historico chat");
		
		frame.setResizable (false);
		frame.setBounds(100,100,273,128);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		panel.setLayout(null);
		
	}
	
	public VentanaChat() {
		this.initialize();
		this.setVisible(false);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
