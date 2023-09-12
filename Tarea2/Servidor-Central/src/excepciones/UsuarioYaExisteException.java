package excepciones;

@SuppressWarnings("serial")
public class UsuarioYaExisteException extends Exception {

	public UsuarioYaExisteException(String message) {
		super(message);
	}
}
