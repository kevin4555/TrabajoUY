package excepciones;

@SuppressWarnings("serial")
public class UsuarioYaExistePostulacion extends Exception{

	public UsuarioYaExistePostulacion(String message) {
		super(message);
	}

}
