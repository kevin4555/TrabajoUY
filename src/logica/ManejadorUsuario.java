package logica;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	
	public static ManejadorUsuario getinstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
	}
	
}
