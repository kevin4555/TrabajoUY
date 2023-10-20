package excepciones;

/**
 * Excepción PaquetePublicacionYaFueComprado.
 */

public class PaquetePublicacionYaFueComprado
    extends Exception {
  
  public PaquetePublicacionYaFueComprado(String string) {
    super(string);
  }
}
