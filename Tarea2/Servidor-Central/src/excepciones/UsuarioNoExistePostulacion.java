package excepciones;

@SuppressWarnings("serial")
public class UsuarioNoExistePostulacion extends Exception{

	public UsuarioNoExistePostulacion(String message) {
		super(message);
	}

}
