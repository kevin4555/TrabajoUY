package excepciones;

/**
 * Excepción TipoPublicacionYaExisteException.
 */

public class TipoPublicacionYaExisteException
      extends Exception {
  public TipoPublicacionYaExisteException(String message) {
    super(message);
  }
}
