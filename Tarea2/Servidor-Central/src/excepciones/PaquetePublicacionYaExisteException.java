package excepciones;

/**
 * Excepción PaquetePublicacionYaExisteException.
 */

@SuppressWarnings("serial")
public class PaquetePublicacionYaExisteException
    extends Exception {
  
  public PaquetePublicacionYaExisteException(
      String string) {
    super(string);
  }
}
