package Cliente;

import CapaGrafica.VentanaPrincipal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import CapaGrafica.VentanaChat;

import CapaLogica.IFachada;

public class MainCliente {
	
	public static void main (String args []) {
		try {
			String ip = "localhost";
            String puerto = "1099";  
            String url = "//" + ip + ":" + puerto + "/fachada";
            IFachada fachada = (IFachada) Naming.lookup(url);
            
            System.out.println("Cliente conectado al servidor en: " + url);
            
            VentanaPrincipal ventana = new VentanaPrincipal(fachada);
            ventana.setVisible(true);
	
		}	catch (MalformedURLException e) { e.printStackTrace(); }
			catch (RemoteException e) { e.printStackTrace(); }
			catch (NotBoundException e) { e.printStackTrace(); } 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
}