package excepciones;

@SuppressWarnings("serial")
public class OfertaLaboralYaExisteException extends Exception {
	
	public OfertaLaboralYaExisteException(String mensaje) {
		super(mensaje);
	}

}
