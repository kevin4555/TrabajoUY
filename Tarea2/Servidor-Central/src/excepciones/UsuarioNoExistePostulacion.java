package excepciones;

/**
 * Excepción UsuarioNoExistePostulacion.
 */

@SuppressWarnings("serial")
public class UsuarioNoExistePostulacion extends Exception {
  
  public UsuarioNoExistePostulacion(String message) {
    super(message);
  }
  
}
