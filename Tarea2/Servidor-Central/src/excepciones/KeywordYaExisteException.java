package excepciones;

/**
 * Excepción KeywordYaExisteException.
 */

@SuppressWarnings("serial")
public class KeywordYaExisteException extends Exception {

  public KeywordYaExisteException(String message) {
    super(message);
  }

}
