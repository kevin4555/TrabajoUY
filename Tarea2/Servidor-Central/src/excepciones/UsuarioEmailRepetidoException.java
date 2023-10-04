package excepciones;

/**
 * Excepción UsuarioEmailRepetidoException.
 */

@SuppressWarnings("serial")
public class UsuarioEmailRepetidoException extends Exception {
  
  public UsuarioEmailRepetidoException(String message) {
    super(message);
  }
}
