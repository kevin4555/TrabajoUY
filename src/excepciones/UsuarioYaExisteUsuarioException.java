package excepciones;

@SuppressWarnings("serial")
public class UsuarioYaExisteUsuarioException extends Exception {

	public UsuarioYaExisteUsuarioException(String message) {
		super(message);
	}
}
