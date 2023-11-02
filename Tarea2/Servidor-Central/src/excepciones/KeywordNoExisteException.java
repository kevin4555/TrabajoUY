package excepciones;

/**
 * Excepción KeywordNoExisteException.
 */

@SuppressWarnings("serial")
public class KeywordNoExisteException extends Exception {

  public KeywordNoExisteException(String message) {
    super(message);
  }

}
