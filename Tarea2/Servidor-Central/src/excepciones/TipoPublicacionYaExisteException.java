package excepciones;

@SuppressWarnings("serial")
public class TipoPublicacionYaExisteException extends Exception {
	public TipoPublicacionYaExisteException(String message) {
		super(message);
	}
}
