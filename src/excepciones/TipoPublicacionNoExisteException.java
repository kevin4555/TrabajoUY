package excepciones;

import logica.OfertaLaboralNoExisteException;

@SuppressWarnings("serial")
public class TipoPublicacionNoExisteException extends Exception {
	
	public TipoPublicacionNoExisteException(String mensaje) {
        super(mensaje);
    }
	
	public OfertaLaboralNoExisteException(String mensaje)
	{
		super(mensaje);
	}
}
