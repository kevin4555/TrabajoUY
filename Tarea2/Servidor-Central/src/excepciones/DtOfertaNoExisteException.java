package excepciones;

/**
 * Excepción DtOfertaNoExisteException.
 */

@SuppressWarnings("serial")
public class DtOfertaNoExisteException extends Exception {
  
  public DtOfertaNoExisteException(String string) {
    super(string);
  }
  
}
