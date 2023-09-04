package excepciones;

@SuppressWarnings("serial")
public class UsuarioEmailRepetidoException extends Exception{

	public UsuarioEmailRepetidoException(String message) {
		super(message);
	}
}
