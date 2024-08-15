package persistencia;

public class FachadaPersistencia {
	private static FachadaPersistencia instancia;

	private FachadaPersistencia()
	{
		new Persistencia();
	}

	//Singleton
	public static FachadaPersistencia getInstancia()
	{
		if (instancia == null)
			instancia = new FachadaPersistencia();	
		return instancia;
	}
}