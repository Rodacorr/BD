package CapaLogica;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import persistencia.Persistencia;
import CapaLogica.Monitor;

public class Fachada extends UnicastRemoteObject implements IFachada{

	private List<String> mensajes = new ArrayList<>();
	//private static final String FILE_PATH = "mensajes.txt";
	
	private static final long serialVersionUID = 1L;
	
	private Monitor m;
	
	public Fachada() throws RemoteException{
		m = new Monitor();
	}
	
	public void ingresarMensaje(String mensaje) {
		mensajes.add(mensaje);
	}
	
	public List<String> listarMensajes(){
		return new ArrayList<>(mensajes);
	}

	@Override
	public void respaldarMensaje() {
		
		m.comienzoEscritura();
		Persistencia p = new Persistencia();
		
		try {//(FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
			
			Properties prop = new Properties();
			String nomArch = "config/datos.dat";
			//for (String mensaje : mensajes) {
            //    fos.write((mensaje + "\n").getBytes()); 
            //}
			
			prop.load (new FileInputStream (nomArch));
			//String nombreArchivo = prop.getProperty("nombreArchivo");
			p.guardarmensajes(mensajes, nomArch);
			m.terminoEscritura();
			
        } catch (IOException e) {
            System.out.println("Error al respaldar mensajes: " + e.getMessage());
            m.terminoEscritura();
        }
	}

	@Override
	public void recuperarMensaje() {
		
		m.comienzoEscritura();
		Persistencia p = new Persistencia();
		
        try {//(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
        	Properties prop = new Properties();
			String nomArch = "config/datos.dat";
			prop.load (new FileInputStream (nomArch));
			
			String nombreArchivo = prop.getProperty("nombreArchivo");
			
        	//String line;
            //while ((line = reader.readLine()) != null) {
            //    mensajes.add(line);
            //}
			
			mensajes.addAll(p.levantarMensajes(nombreArchivo));
			//p.levantarMensajes(nombreArchivo);
			m.terminoEscritura();
			
        } catch (IOException e) {
            System.out.println("Error al recuperar mensajes: " + e.getMessage());
            m.terminoEscritura();
        }
	}
	
}