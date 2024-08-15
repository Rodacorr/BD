package CapaGrafica;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.*;

import CapaLogica.IFachada;

public class VentanaPrincipal {
	
	private JLabel labelMensaje;
	private JTextField textFieldMensaje;
	private JButton btnIngresar;
	private JButton btnRespaldar;
	private JButton btnRecuperar;
	private JButton btnListarMensajes;
	private JFrame frame;
	private JTextArea areaTexto;
	private IFachada fachada;
	
	public VentanaPrincipal(IFachada fachada) {
		this.fachada = fachada;
		this.initialize();
		this.setVisible(false);
	}
	
	private void initialize() {
		frame = new JFrame(); 
		Container panel = frame.getContentPane(); 
		panel.setBackground(new Color(240,248,255));
		frame.setTitle("Chat");
		
		frame.setResizable (true);
		frame.setBounds(100,100,500,400);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
																		
		panel.setLayout(null);
		
		labelMensaje = new JLabel ("Mensaje:");
		labelMensaje.setBounds(10,11,83,14);
		panel.add(labelMensaje);
		
		textFieldMensaje = new JTextField();
		textFieldMensaje.setBounds(103,8,250,20);
		panel.add(textFieldMensaje);
		
		btnIngresar = new JButton ("ENVIAR");
		btnIngresar.setFont (new Font ("Arial",Font.BOLD,12));
		btnIngresar.setBackground (new Color(173,216,230));
		btnIngresar.setBounds (360,8,100,23);
		
		EnviaTexto mievento = new EnviaTexto();
		btnIngresar.addActionListener(mievento);
		
		panel.add (btnIngresar);

		textFieldMensaje.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		
		areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		areaTexto.setLineWrap(true);
		areaTexto.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBounds(10, 50, 460, 250);
        panel.add(scrollPane);

		btnRespaldar = new JButton("RESPALDAR");
		btnRespaldar.setBounds(10, 320, 140, 23);
		panel.add(btnRespaldar);
		btnRespaldar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fachada.respaldarMensaje();
					JOptionPane.showMessageDialog(frame, "Mensajes respaldados exitosamente.");
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnRecuperar = new JButton("RECUPERAR");
		btnRecuperar.setBounds(170, 320, 140, 23);
		panel.add(btnRecuperar);
		btnRecuperar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fachada.recuperarMensaje();
					JOptionPane.showMessageDialog(frame, "Mensajes recuperados exitosamente.");
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
			}
		});

		btnListarMensajes = new JButton("LISTAR MENSAJES");
		btnListarMensajes.setBounds(330, 320, 140, 23);
		panel.add(btnListarMensajes);
		btnListarMensajes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<String> mensajes = fachada.listarMensajes();
					areaTexto.setText("");
					for (String mensaje : mensajes) {
						areaTexto.append(mensaje + "\n");
					}
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	private class EnviaTexto implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String mensaje = textFieldMensaje.getText();
			if (!(mensaje.length() == 0)) {
				try {
					fachada.ingresarMensaje(mensaje);
					areaTexto.append(mensaje + "\n");
					textFieldMensaje.setText("");
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}