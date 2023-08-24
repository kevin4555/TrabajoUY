package excepciones;

@SuppressWarnings("serial")
public class CompraPaqueteYaExisteException extends Exception {

	public CompraPaqueteYaExisteException(String message) {
		super(message);
	}
}
