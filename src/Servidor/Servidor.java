package Servidor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import CapaLogica.Fachada;
import CapaLogica.IFachada;

public class Servidor {

	public Servidor() {
		
	}
	
	public static void main(String[] args){
	try {
		LocateRegistry.createRegistry(1099);
		
		IFachada fachada = new Fachada();
		
		try {
			fachada.recuperarMensaje();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Properties prop = new Properties();
		String nomArch = "config/txt.properties.txt";
		prop.load(new FileInputStream(nomArch));
		String ip = prop.getProperty("ip","localhost");
		//String ip = prop.getProperty("localhost");
		String puerto = prop.getProperty("puerto");
		
		System.out.println ("Publicando fachada...");
		Naming.rebind("//" + ip + ":" + puerto + "/fachada", fachada);
		System.out.println ("Fachada publicada en RMI con éxito");
	}
	catch (RemoteException e)
	{ e.printStackTrace(); }
	catch (MalformedURLException e)
	{ e.printStackTrace(); 
	} catch (FileNotFoundException e) 
	// TODO Auto-generated catch block
	{e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
	e.printStackTrace();
	}
		
	}
}

//coment