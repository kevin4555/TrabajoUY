package excepciones;

@SuppressWarnings("serial")
public class UsuarioNoExisteUsuarioException extends Exception {

	public UsuarioNoExisteUsuarioException(String message) {
		super(message);
	}
}
