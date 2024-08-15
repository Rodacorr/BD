package persistencia;

import java.io.Serializable;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Config implements Serializable{
	private String archivoConf;
	private static Config instancia;


	public static Config getInstancia(){
		if (instancia == null)
			instancia = new Config();
		return instancia;
	}

	public String getArchivoConf(String properties) throws IOException{

		try { 
			Config conf = Config.getInstancia();

			Properties prop = new Properties();
			prop.load(new FileInputStream(conf.getArchivoConf()));
			String rutaArchivo = prop.getProperty(properties);
			return rutaArchivo;
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			throw e;
		}
	}

	public String getArchivoConf() {
		return archivoConf;
	}

	public void setArchivoConf(String archivoConf) {
		this.archivoConf = archivoConf;
	}
}
