package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

	//private List<String> mensajes;
	//private static final String FILE_PATH = "txt.properties";
	
	public void guardarmensajes(List<String> mensajes, String nomArch) {
		try(FileOutputStream fos = new FileOutputStream(nomArch)){
			for (String mensaje : mensajes) {
				fos.write((mensaje + "\n").getBytes());
			}
		}catch (IOException e) {
			System.out.println("Error al guardar mensajes: " + e.getMessage());
		}
	}
	
	public List<String> levantarMensajes(String nombreArchivo) throws IOException {
	    String rutaArchivo = "config/datos.dat";

	    if (rutaArchivo == null || rutaArchivo.isEmpty()) {
	        throw new FileNotFoundException("La ruta del archivo es nula o vacía");
	    }

	    File archivo = new File(rutaArchivo);
	    if (!archivo.exists()) {
	        throw new FileNotFoundException("El archivo no se encuentra en: " + rutaArchivo);
	    }

	    BufferedReader reader = new BufferedReader(new FileReader(archivo));
	    List<String> mensajes = new ArrayList<>();
	    String linea;
	    while ((linea = reader.readLine()) != null) {
	        mensajes.add(linea);
	    }
	    reader.close();
	    return mensajes;
	}
}
