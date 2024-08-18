package CapaLogica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IFachada extends Remote {
	
	public void ingresarMensaje(String mensaje) throws RemoteException;
	
	public List<String> listarMensajes() throws RemoteException;
	
	public void respaldarMensaje() throws RemoteException;
	
	public void recuperarMensaje() throws RemoteException;
}

//coment