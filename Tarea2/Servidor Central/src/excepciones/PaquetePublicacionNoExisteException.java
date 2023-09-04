package excepciones;

@SuppressWarnings("serial")
public class PaquetePublicacionNoExisteException extends Exception {
	public PaquetePublicacionNoExisteException(String string) {
		super(string);
	}
}
